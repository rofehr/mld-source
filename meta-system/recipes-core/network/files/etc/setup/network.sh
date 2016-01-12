#!/bin/sh

case $1 in
	setserver)
		update_server.sh
		;;
	interfaces)
		ls /sys/class/net/ | grep eth
		;;
	restart)
		NETWORK_IP=$(echo "$NETWORK_IP" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		NETWORK_NETMASK=$(echo "$NETWORK_NETMASK" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		NETWORK_GATEWAY=$(echo "$NETWORK_GATEWAY" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		NETWORK_DNS=$(echo "$NETWORK_DNS" | sed "s/\(^\|\.\)0\+\([^\.]\)/\1\2/g")
		restart network
		;;
esac
