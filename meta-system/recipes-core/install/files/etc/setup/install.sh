#!/bin/sh

case $1 in
	hdd_list)
		echo "-"
		fdisk -l | grep "^Disk /dev/\S\+:" | sed "s/.*\/dev\/\(.*\):.*/\1/" | sort -u | while read dev; do grep "$dev$" /proc/partitions | while read _ _ size dev; do size=$(($size/1024*1000/1024)); echo "$dev: $(($size/1000)).$(($size%1000/100))GB"; done ;done
		;;
	partition_list)
		echo "-"
		fdisk -l | grep "^Disk /dev/\S\+:" | sed "s/.*\/dev\/\(.*\):.*/\1/" | sort -u | while read dev; do grep "$dev." /proc/partitions | grep -v "$(readlink /dev/boot || readlink /dev/root | cut -d/ -f3)" | while read _ _ size dev; do size=$(($size/1024*1000/1024)); echo "$dev: $(($size/1000)).$(($size%1000/100))GB"; done ;done
		;;
	mmc_partition_list)
		echo "-"
		fdisk -l | grep "^Disk /dev/\S\+:" | sed "s/.*\/dev\/\(.*\):.*/\1/" | sort -u | while read dev; do grep "$dev" /proc/partitions | grep -v "mmcblk0p1" | while read _ _ size dev; do size=$(($size/1024*1000/1024)); echo "$dev: $(($size/1000)).$(($size%1000/100))GB"; done ;done
		;;
	boot_partition_list)
		echo "-"
		fdisk -l | grep "^Disk /dev/mmcblk\S:" | sed "s/.*\/dev\/\(.*\):.*/\1/" | sort -u | while read dev; do grep "$dev\(p1\)\?$" /proc/partitions | while read _ _ size dev; do size=$(($size/1024*1000/1024)); echo "$dev: $(($size/1000)).$(($size%1000/100))GB"; done ;done
		;;
	install)
		. /etc/init.d/rc.functions
		test "$INSTALL_ON_SDCARD" && export INSTALL_HDD="mmcblk0"
		grep -q vdr /proc/$(cut -d " " -f 4 /proc/$PPID/stat)/cmdline && parent=vdr
		grep -q webserver /proc/$PPID/cmdline && parent=webserver
		/usr/sbin/install.sh $2 $parent
		check_status
		;;
esac
