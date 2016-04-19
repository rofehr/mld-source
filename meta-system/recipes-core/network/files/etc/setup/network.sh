#!/bin/sh

case $1 in
	setserver)
		update_server.sh
		;;
	interfaces)
		echo "-"
		ls /sys/class/net/ | grep -v lo
		;;
	restart)
		NETWORK_STATIC_IP=$(echo "$NETWORK_STATIC_IP" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		NETWORK_STATIC_NETMASK=$(echo "$NETWORK_STATIC_NETMASK" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		NETWORK_STATIC_GATEWAY=$(echo "$NETWORK_STATIC_GATEWAY" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		NETWORK_STATIC_DNS=$(echo "$NETWORK_STATIC_DNS" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		if [ -z "${NETWORK_STATIC_INTERFACE##eth*}" ]; then
			restart network
		else
			restart network-wireless
		fi
		;;
esac
