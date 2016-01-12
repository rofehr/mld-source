#!/bin/sh

. /etc/init.d/rc.functions

if [ -n "$1" ]; then
	update_setting "NETWORK_SERVER_IP" "$1"
fi

if [ -n "$NETWORK_SERVER_IP" ]; then
	ping -c 1 -W 1 $NETWORK_SERVER_IP > /dev/null && update_setting "NETWORK_SERVER_MAC" "$(LC_ALL=C arp -a $NETWORK_SERVER_IP | cut -d " " -f 4)"
fi
