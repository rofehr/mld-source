#!/bin/sh

case $1 in
	setclass)
		. /etc/rc.config
		sed "s/files \S\+/files $APT_PACKAGE_CLASS/" -i /etc/apt/sources.list
		apt-get update
		;;
esac
