#!/bin/sh
#
# setting up the web setup menu.

. /etc/init.d/rc.functions
TEXTDOMAIN="webserver-update_quick_start.sh"

ps=$(ps); echo "$ps" | grep "{update_quick_st" | grep -q -v $$ && { echo "${0##*/} is already running">&2; exit 1; }

setupdir="${1-/etc/setup}"
websetupdir="${2-/var/www/tpl/quickstart}"
tmpdir="${3-/tmp/setup.$$}"

# xml-Daten als Verzeichnisstruktur abbilden
rm -rf $tmpdir
mkdir $tmpdir
cd $tmpdir
for file in $setupdir/*.xml; do
	grep . "$file" | while read line; do
		name=${file##*/}
		TEXTDOMAIN="${name%.*}-$name"
		if [ -z "${line#*<menu name=\"*\">}" ]; then
			name=`echo "$line" | sed 's/.* name="\($(g\?tr\? '"'"'\)\?\([^'"'"'"]*\).*/\2/'`
			mkdir -p "$name"
			cd "$name"
			sed 's/ name="[^"]*"//' -i args.xml 2>/dev/null
			echo -n " name=\"$(gt "$name")\"" >> args.xml
			if [ -z "${line#*command=\"*\">}" ]; then
				command="${line#*command=\"}"
				command="${command%%\"*}"
				echo -n " command=\"$command\"" >> args.xml
			fi
		elif [ -z "${line#*</menu>}" ]; then
			cd ..
		elif  [ -z "${line##*quickstart=\"1\"*}" -o -n "$select" -a -z "${line#*</value>}" -o -n "$select" -a -z "${line#*</entry>}" ]; then
			eval "echo \"$(echo "$line" | sed 's/ help2=\"[^\"]*\"//;s/"/\\"/g')\"" >> menu.xml
			eval "echo -en \"$(echo "$line" | grep " help2=" | sed 's/.* help2=\"\([^\"]*\)\".*/<p>\1<\/p>\\n/')\"" >> help.html
			select=1
		else
			select=
		fi
	done
done
touch end.xml

# Verzeichnisstruktur in web datein schreiben
rm -rf $websetupdir
mkdir $websetupdir
find $tmpdir/* -name menu.xml 2>/dev/null | while read file; do
	dir="${file%/menu.xml}"
	path="${dir#$tmpdir}"
	name="`sed 's/.*name="\([^\"]*\)".*\|.*/\1/' "$dir/args.xml"`"
	base_command="`sed 's/.*command="\([^\"]*\)".*\|.*/\1/' "$dir/args.xml"`"

	test -s "$dir/help.html" && cp "$dir/help.html" "$websetupdir/$path/help.html"

	mkdir -p "$websetupdir$path"
	echo "<h2 class='row'>$name</h2>" >> "$websetupdir/$path/20_headline.sh"

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
				echo "<div id='button_$name' class='button'><input type=\"button\" value=\"$name\" onclick=\"call ('$command')\" title=\"$help1\"/></div>" >>$out
			else 
				if [ "$base_command" ]; then
					echo "<input type=\"hidden\" name=\"command_${config%%_*}\" value=\"$base_command\">" >> "$websetupdir/$path/30_setup.sh"
					base_command=
				fi
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
done
echo "<div class=\"button\"><input type=\"submit\" value=\"$(gt "Save")\"/></div>" > "$websetupdir/zz_90_save.sh"
cp -r $websetupdir.d/* $websetupdir 2>/dev/null
if [ "$(ls "$websetupdir/20_"*.sh 2>/dev/null)" ]; then
	echo "<ul class='row navi'>" > "$websetupdir/15_navi.sh"
	echo "</ul>" > "$websetupdir/25_navi.sh"
fi

rm -r $tmpdir
