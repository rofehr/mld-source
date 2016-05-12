#!/bin/sh
#
# argument 1: wanted action, one of mount,unmount,eject,status
# argument 2: mountpoint to act on

action="$1"
path="$2"

case "$action" in
	mount)
		dev=${path##*/}
		eject -t "/dev/$dev" 2>/dev/stdlog # close the tray
		mount "$path" || exit 1            # mount it
		;;
	unmount)
		umount "$path" || exit 1           # unmount it
		;;
	eject)
		dev=${path##*/}
		eject "/dev/$dev" || exit 1        # eject disk
		;;
	status)
		cat /proc/mounts | grep -q "$path" # check if mounted
		if [ $? -ne 0 ]; then              # not mounted ...
			exit 1
		fi
esac

exit 0
