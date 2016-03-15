#!/bin/sh
#
# setting up the web setup menu.

. /etc/init.d/rc.functions
TEXTDOMAIN="webserver-update_web_setup.sh"

ps=$(ps); echo "$ps" | grep "{update_web_setu" | grep -q -v $$ && { echo "${0##*/} is already running">&2; exit 1; }

setupdir="${1-/etc/setup}"
websetupdir="${2-/var/www/tpl/setup}"
tmpdir="${3-/tmp/setup.$$}"

# xml-Daten als Verzeichnisstruktur abbilden
rm -rf $tmpdir
mkdir $tmpdir
cd $tmpdir

#(cd $websetupdir.d; find * -type d) | while read path; do 
#	name="${path##*/}"
#	TEXTDOMAIN="$(grep TEXTDOMAIN $path/* 2>/dev/null | head | sed 's/.*TEXTDOMAIN="\([^"]*\)".*/\1/' | head -n1)" #'
#	mkdir -p "$path"
#	touch "$path/menu.xml"
#	echo -n " name=\"$(gt "$name")\"" > "$path/args.xml"
#done

for file in $setupdir/*.xml; do
	grep . "$file" | while read line; do
		name=${file##*/}
		TEXTDOMAIN="${name%.*}-$name"
		if [ -z "${line#*<menu name=\"*\">}" ]; then
			name=`echo "$line" | sed 's/.* name="\($(gt '"'"'\)\?\([^'"'"'"]*\).*/\2/'`
			mkdir -p "$name"
			cd "$name"
			sed 's/ name="[^"]*"//' -i args.xml 2>/dev/null
			echo -n " name=\"$(gt "$name")\"" >> args.xml
			if [ -z "${line#*command=\"*\">}" ]; then
				command="${line#*command=\"}"
				command="${command%%\"*}"
				echo -n " command=\"$command\"" >> args.xml
			fi
			touch menu.xml
		elif [ -z "${line#*</menu>}" ]; then
			cd ..
		else
			eval "echo \"$(echo "$line" | sed 's/ help2=\"[^\"]*\"//;s/"/\\"/g')\"" >> menu.xml
			eval "echo -en \"$(echo "$line" | grep " help2=" | sed 's/.* help2=\"\([^\"]*\)\".*/<p>\1<\/p>\\n/')\"" >> help.html
		fi
	done
done
touch end.xml

# VDR-Plugins hinzufügen
if [ -e $tmpdir/TV/VDR ]; then
	cd /tmp
	plugindir=$tmpdir/TV/VDR/Plugin-Arguments
	mkdir -p $plugindir
	[ -e $plugindir/args.xml ] || echo -n " name=\"Plugin-Arguments\"" >> $plugindir/args.xml
	vdr -L /usr/lib/vdr/ -h 2>/dev/null | grep "Plugins:" -A1000 | grep "^$" -A1000 > /tmp/vdrhelp.$$
	vdr -L /usr/lib/vdr/ -V 2>/dev/null | grep "vdr " -A1000 | sort | while read line; do
		name="${line%% *}"
		help1="${line#* - }"
		if [ "$name" = "vdr" ]; then
			var="VDR_ARGS"
		else
			var="VDR_PLUGIN_ARGS_${name%%-*}"
		fi
		rm -rf /usr/share/doc/$name/setting
		mkdir -p /usr/share/doc/$name
		grep "^$name" -A100 /tmp/vdrhelp.$$ | while read help; do
			if [ -n "$help" -a -z "${help##[a-z]*}" -a -n "${help##$name*}" ]; then
				break
			fi
			IFS=""
			echo "$help" >> /usr/share/doc/$name/setting
		done
		
		echo "<entry name=\"$name\" sysconfig=\"$var\" type=\"text\" value=\"\" help1=\"$help1\" />" >> "$plugindir/menu.xml"
	done
	rm /tmp/vdrhelp.$$
fi

# Verzeichnisstruktur in web datein schreiben
rm -rf $websetupdir
mkdir $websetupdir
find $tmpdir/* -type d 2>/dev/null | sort | while read dir; do
	path="${dir#$tmpdir}"
	mkdir -p "$websetupdir$path"
	name="`sed 's/.*name="\([^\"]*\)".*\|.*/\1/' "$dir/args.xml"`"
	file="$websetupdir${path%/*}/20_navi.sh"
	echo "<ul class='row navi'>" > "$websetupdir${path%/*}/15_navi.sh"
	echo "<li><a id='navi_${path##*/}' href='?site=setup&section=${path#/}'>$name</a></li>" >> "$websetupdir${path%/*}/20_navi.sh"
	echo "</ul>" > "$websetupdir${path%/*}/25_navi.sh"
done
echo "<h2 style='display:none'>$(gt 'Setup')</h2>" >> "$websetupdir/$path/10_headline.sh"
find $tmpdir/* -name menu.xml 2>/dev/null | while read file; do
	dir="${file%/menu.xml}"
	path="${dir#$tmpdir}"
	name="`sed 's/.*name="\([^\"]*\)".*\|.*/\1/' "$dir/args.xml"`"
	command="`sed 's/.*command="\([^\"]*\)".*\|.*/\1/' "$dir/args.xml"`"

	test -s "$dir/help.html" && cp "$dir/help.html" "$websetupdir/$path/help.html"

	p=${path%/*}                                                                                            
	navi=                                                                                               
	while [ -n "$p" ]; do
		navi="<a href='?site=setup&section=${p#/}'>`sed 's/.*name="\([^\"]*\)".*\|.*/\1/' "$tmpdir$p/args.xml"`</a> &ndash; $navi"
		p=${p%/*}
	done
	echo "<h2><a href='?site=setup'>$(gt 'Setup')</a> &ndash; $navi $name</h2>" >> "$websetupdir/$path/10_headline.sh"

	test -n "$command" && echo "<input type=\"hidden\" name=\"command\" value=\"$command\">" >> "$websetupdir/$path/30_setup.sh"
	cat "$file" | while read line; do
		if [ -z "${line##*<entry*}" ]; then
			name="`echo "$line" | sed 's/.* name="\([^\"]*\)" .*\|.*/\1/'`"
			config="`echo "$line" | sed 's/.* sysconfig="\([^\"]*\)" .*\|.*/\1/'`"
			value="`echo "$line" | sed 's/.* value="\([^\"]*\)" .*\|.*/\1/'`"
			text="`echo "$line" | sed 's/.* text="\([^\"]*\)" .*\|.*/\1/'`"
			help1="`echo "$line" | sed 's/.* help1="\([^\"]*\)" .*\|.*/\1/'`"
			setup="`echo "$line" | sed 's/.* setup="\([^\"]*\)" .*\|.*/\1/'`"
			multi="`echo "$line" | sed 's/.* multi="\([^\"]*\)" .*\|.*/\1/'`"
			type="`echo "$line" | sed 's/.* type="\([^\"]*\)" .*\|.*/\1/'`"
			edit="`echo "$line" | sed 's/.* edit="\([^\"]*\)" .*\|.*/\1/'`"
			command="`echo "$line" | sed 's/.* command="\([^\"]*\)" .*\|.*/\1/'`"
			order="`echo "$line" | sed 's/.* order="\([^\"]*\)" .*\|.*/\1/'`"
			value="\${$config-$value}"

			if [ "$type" = "button" ]; then
				out="$websetupdir/$path/$((91+${order:-0}))_setup.sh"
				echo "<div id='button_$name' class='button'><input type=\"button\" value=\"$name\" onclick=\"call ('${command%%,*}', '${command##*,}')\" title=\"$help1\"/></div>" >>$out
			elif [ "$type" = "save" ]; then
				out="$websetupdir/$path/90_save.sh"
				echo "<div id='button_$name' class='button'><input type=\"submit\" value=\"$name\"/></div>" > $out
			else
				out="$websetupdir/$path/$((30+${order:-0}))_setup.sh"
				test -n "$command" && echo "<input type=\"hidden\" name=\"${config}_command\" value=\"$command\">" >>$out
				if [ "$type" = "bool" ]; then
					echo "<div id='setup_$config' class='row'><label title=\"$help1\">$name:</label><input type=\"checkbox\" name=\"$config\" value=\"1\" \$(test -n \"$value\" -a \"$value\" != \"0\" && echo checked) onchange=\"store(this)\"/></div>" >>$out
				elif [ "$type" = "text" -o "$type" = "numtext" -o "$type" = "number" -o "$type" = "ip" ]; then
					echo "<div id='setup_$config' class='row'><label title=\"$help1\">$name:</label><input type=\"text\" name=\"$config\" value=\"$value\" onchange=\"store(this)\"/></div>" >>$out
				elif [ "$type" = "selection" ]; then
					if [ -n "$multi" -o -n "$edit" ]; then
						echo "<div id='setup_$config' class='row'><label title=\"$help1\">$name:</label><div><input type=\"text\" name=\"$config\" value=\"$value\" onchange=\"store(this)\"/><select name=\"select_$config\" onchange=\"setValue(this, '$multi')\">" >>$out
					else
						echo "<div id='setup_$config' class='row'><label title=\"$help1\">$name:</label><select name=\"$config\" value=\"$value\" onchange=\"store(this)\">" >>$out
					fi
					if [ -n "$setup" ]; then
						echo "<? $setup | sed 's/\(.*\)/<option>\1<\/option>/' ?>" >>$out
						echo "</select></div>" >>$out
						if [ -n "$multi" -o -n "$edit" ]; then
							echo "</div>" >>$out
							echo "<script>selectAndInput (\"select_$config\")</script>" >>$out
						fi
					fi
				elif [ "$type" = "info" ]; then
					echo "<h3>$name</h3>" >>$out
					echo "<p>$text</p>" >>$out
				fi
			fi
		elif [ -z "${line##*<value*}" ]; then
			if [ -z "${line##*<value name=*}" ]; then
				echo $line | sed 's/<value name="\(.*\)">\(.*\)<\/value>/<option value="\2">\1<\/option>/' >>$out
			else
				echo $line | sed 's/<value>\(.*\)<\/value>/<option>\1<\/option>/' >>$out
			fi
		elif [ -z "${line##*</entry>*}" ]; then
			echo "</select></div>" >>$out
		fi
	done
	if [ "$(ls "$websetupdir/$path/"*_setup.sh 2>/dev/null)" ]; then
		out="$websetupdir/$path/90_save.sh"
		test -e "$out" || echo "<div class=\"button\"><input type=\"submit\" value=\"$(gt 'Save')\"/></div>" > $out
	fi
done
cp -r $websetupdir.d/* $websetupdir 2>/dev/null

rm -r $tmpdir
