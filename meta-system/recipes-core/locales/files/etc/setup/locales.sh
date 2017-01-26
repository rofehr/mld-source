#!/bin/sh

case $1 in
	setlang)
		. /etc/init.d/rc.functions
		update_config "LANG" "export LANG='$LANG'" /etc/profile
		update_config "LC_ALL" "export LC_ALL='$LANG'" /etc/profile
		grep -q "OSDLanguage = $" /etc/vdr/setup.conf 2>/dev/null && runvdr -r &>/dev/null </dev/null &
		;;
	timezones)
		echo "-"
		find /usr/share/zoneinfo/ -type f | cut -d / -f 5-
		;;
	settimezone)
		. /etc/init.d/rc.functions
		if [ -n "$TIMEZONE" ]; then
			ln -fs /usr/share/zoneinfo/$TIMEZONE /etc/localtime
		else
			rm -f /etc/localtime
		fi
		;;
	setkeymap)
		. /etc/init.d/rc.functions
		if [ -n "$KEYMAP" ]; then
			loadkeys "$KEYMAP" >>/dev/null 2>&1
		fi
		;;
esac