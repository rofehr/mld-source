#!/bin/sh

mimetype()
{
	mimetype=$(grep "^$1" <<- EOF
		txt text/plain
		sh text/plain
		html text/html
		shtml text/html
		tgz application/x-compressed-tar
		css text/css
		js text/javascript
		gif image/gif
		jpg image/jpg
		png image/png
		ico image/x-icon
		EOF
	)
	echo ${mimetype#* }
}

header()
{
	file=/tmp/webserver.$$.header
	if [ -n "$1" -a -e $file ] && grep -q "${1%% *}" $file; then
		sed "s|${1%% *}.*|$1|" -i $file
	else
		echo -e "$1\r" >>$file
	fi
	test -z "$1" && { cat $file; rm $file; }
}


urldecode=${urldecode-urldecode}

# parse request
read request_method uri protocol
test -z "${uri##*\?*}" && query=${uri#*\?}
query=${query%%#*}
test -z "${uri##*#*}" && search=${uri#*#}
uri=${uri%%#*}
uri=${uri%%\?*}
uri=${uri#/}
uri="$(echo "$uri" | sed "s/%20/ /g")"
test -d "$uri" && uri=$uri/index.sh
test -z "$uri" && uri=index.sh
type=${uri##*/}
test -z "${type##*.*}" && type=${type##*.} || type=""
document_root=$(pwd)

# .htaccess regeln bearbeiten
test -f .htaccess && . .htaccess

# send response for simple file types
if [ ! -e "$uri" ]; then
	while read line && test "$line" != $'\r'; do :;done
	header "HTTP/1.0 404 File not found"
	header "Content-Type: text/plain"
	header
	echo "File not found: $uri"
	exit
elif [ "$type" != "shtml" ]; then
	while read line && test "$line" != $'\r'; do :;done
	header "HTTP/1.0 200 OK"
	header "Content-Type: $(mimetype $type)"

	case "$type" in
		sh)
			. "$uri"
			;;
		*)
#			date="$(date -r "$uri" +"%Y-%m-%d %H:%M:%S")"
#			if [ ! "$date" \> "$HTTP_IfModifiedSince" ]; then
#				header "HTTP/1.0 304 Not Modefied"
#				header
#			else
				header "Cache-Control: max-age=86400"
#				header "Last-Modified: $date"
				header
				cat "$uri"
#			fi
			;;
	esac
	exit
fi


# parse header
eval "$(echo $query | sed 's/&$\|&&//g;s/&\?\([^=]\+\)\(=\([^&]*\)\)\?/GET_\1="\3";/g' | $urldecode)"
while read line; do
	test "$line" = $'\r' && break
	eval "$(echo $line | sed 's/\([^:]\+\): \(.*\)./HTTP_\1="\2";/g;s/^\([^=-]*\)-/\1/g;s/^\([^=-]*\)-/\1/g')" 2>/dev/null
	header="$header$line"
done
test -z "${HTTP_Host##*:*}" && HTTP_Port=${HTTP_Host##*:}
HTTP_Host=${HTTP_Host%%:*}
test -n "$HTTP_Cookie" && eval "$(echo $HTTP_Cookie | sed 's/\(; \)\?\([^=]\+\)\(=\([^;]*\)\)\?/HTTP_Cookie_\2="\4"; /g;s/+/ /g')" 2>/dev/null

# parse body
if [ "$HTTP_ContentLength" ]; then
	HTTP_ContentType=${HTTP_ContentType:-$HTTP_Contenttype}
	dd bs=1 count=$HTTP_ContentLength >/tmp/webserver.$$.body 2>/dev/null
	if [ -z "${HTTP_ContentType##multipart/form-data*}" ]; then
		# multipart form data
		state="start"
		pos=0
		while read -r line; do
			line=${line%$'\r'}
			if [ $state = "start" ]; then
				boundary="$line"
				state="header"
			elif [ $state = "header" ]; then
				if [ -z "$line" ]; then
					pos2=0
					state="body"
				elif [ -z "${line##Content-Disposition: form-data;*}" ]; then
					eval ${line#*; }
					name="POST_$name"
					eval $name=""
					file=""
				elif [ -z "${line##Content-Type:*}" ]; then
					file="/tmp/webserver.$$.$name"
					echo -n > $file
					eval ${name}_type='${line#* }'
					eval ${name}_name='$filename'
					eval ${name}_file='$file'
				fi
			elif [ $state = "body" ]; then
				if [ "${line%--}" = "$boundary" ]; then
					if [ -z "$file" ]; then
						value="$(head -n $pos /tmp/webserver.$$.body | tail -n $pos2)"
						nl=$'\r'
						eval $name='${value%$nl}'
					else
						head -n $pos /tmp/webserver.$$.body | tail -n $pos2 > $file
						dd if=$file bs=1 count=$(($(ls -l $file | cut -b 33-43)-2)) of=$file.tmp 2>/dev/null
						mv $file.tmp $file
					fi
					state="header"
				fi
				pos2=$(($pos2+1))
			fi
			pos=$(($pos+1))
		done < /tmp/webserver.$$.body
	elif [ -z "${HTTP_ContentType##application/x-www-form-urlencoded*}" ]; then
		# url encoded form data
		eval "$(sed 's/&$\|&&//g;s/%22/\\%22/g;s/%24/\\%24/g;s/&\?\([^=]\+\)\(=\([^&]*\)\)\?/POST_\1="\3";/g;s/+/ /g' /tmp/webserver.$$.body | $urldecode)"
	fi
fi


session_start()
{
	if [ -e "/tmp/session_$HTTP_Cookie_sessid" ]; then
		. /tmp/session_$HTTP_Cookie_sessid
	else
		HTTP_Cookie_sessid=$(date | md5sum | cut -d " " -f 1)
		header "Set-Cookie: sessid=$HTTP_Cookie_sessid"
		touch /tmp/session_$HTTP_Cookie_sessid
	fi
	echo $HTTP_Cookie_sessid > /tmp/webserver.$$.session
	find /tmp -name session_* -amin +30 -exec rm {} \; 2>/dev/null
}
session_set()
{
	name=SESSION_$1
	value=$2
	if [ -s /tmp/webserver.$$.session ]; then
		file=/tmp/session_$(cat /tmp/webserver.$$.session)
		if grep -qse "^$name=" $file; then
			sed -ie "s|^$name=.*|$name=\"$value\"|g" $file
		else
			echo "$name=\"$value\"" >> $file
		fi
	fi
	eval "$name=\"$value\""
}
session_unset()
{
	name=SESSION_$1
	if [ -s /tmp/webserver.$$.session ]; then
		file=/tmp/session_$(cat /tmp/webserver.$$.session)
		sed -ie "/^$name=/d" $file 2>/dev/null
	fi
	unset $name
}

out()
{ 
	awk 'NR > 1 { print	h } { h = $0 } END { ORS = ""; print h }'
}

include()
{
	name=$(echo $1 | sed "s/\//_/g")
	{ echo -n "?>"; while [ -n "$1" ]; do cat "$1"; shift; done; echo -n "<? "; } | sed "s/?>/\nout << EOF\n/g;s/<?=/\nEOF\necho -n /g;s/<?\(\s\|\$\)/\nEOF\n/g" >"/tmp/webserver.$$.$name"
	. "/tmp/webserver.$$.$name" 2>&1
}


header "HTTP/1.0 200 OK"
header "Content-Type: $(mimetype $type)"

include "$uri" 2>&1 | { read -n1 c; header; echo -n "$c"; cat; } # | tee /tmp/webserver.$$.response

rm -f /tmp/webserver.$$.*
