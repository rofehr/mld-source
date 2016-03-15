#!/bin/sh

case $1 in
	install_remote)
		. /etc/init.d/rc.functions
		if [ -n "$WEBSERVER_INSTALL_REMOTE" ]; then
			lock apt -y install $WEBSERVER_INSTALL_REMOTE
			update_quick_start.sh
		fi
		sed "/WEBSERVER_INSTALL_REMOTE=/d" -i /etc/rc.config
		;;
esac
