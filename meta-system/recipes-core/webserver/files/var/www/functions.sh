#!/bin/sh

MLD_URL="http://www.minidvblinux.de"
HOST_NAME="MLD"

gt()
{
	export LC_ALL=$LANG
	export TEXTDOMAIN
	eval echo -n "$(gettext "$1")"
}

update_setting()
{
	echo "$1=$2"
}

weblog()
{
	x=$(echo -en "\\033")
	echo "<pre class='log'>"
	while read line; do
		echo "$line" | sed "s/</\&lt;/;s/$x.*;3\(.\)m\(.\+\)$x.*/<span class='state color\1'>\2<\/span>/" | sed "N;s/\n</</"
	done
	echo "</pre>"
}

test -e /etc/init.d/rc.functions && . /etc/init.d/rc.functions
