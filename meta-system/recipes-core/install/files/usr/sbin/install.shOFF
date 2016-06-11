#!/bin/sh

. /etc/init.d/rc.functions


check_status()
{
	status=$?
	if [ $status = 0 ]; then
		echo "------ done ------"
	else
		echo -e "\n====== failed ======\n"
		if [ "$INSTALL_OUT" = "vdr" ]; then
			svdrpsend.sh "PLUG apt INFO $(gt 'the last action failed')" >/dev/null
		elif [ "$INSTALL_OUT" = "dialog" ]; then
			dialog --clear --backtitle "$SYSTEM_NAME" --title "$INSTALL_TITLE" --msgbox "$(gt 'the last action failed')" 8 45 >&3
		else
			echo "$(gt 'the last action failed')" >&3
		fi
	fi
	return $status
}

message()
{
	if [ "$INSTALL_OUT" = "vdr" ]; then
		kill $state_pid 2>/dev/null && sleep 1
		svdrpsend.sh "PLUG apt MESG $1" >/dev/null
		if [ -n "$2" ]; then
			i=0
			while true; do
				test $i -gt 0 && svdrpsend.sh "PLUG apt MESG $1$(echo ' ...' | cut -b 1-$(($i+1))) " >/dev/null
				i=$(($i%3+1))
				sleep 1
			done &
			state_pid=$!
		fi
	elif [ "$INSTALL_OUT" = "dialog" ]; then
		dialog --backtitle "$SYSTEM_NAME" --title "$INSTALL_TITLE" --infobox "$1" 8 45 >&3
	else
		echo $1 >&3
	fi
	echo 
	date
	echo -e "==$1=="
}

info()
{
	if [ "$INSTALL_OUT" = "vdr" ]; then
		kill $state_pid 2>/dev/null && sleep 1
		svdrpsend.sh "PLUG apt INFO $1" >/dev/null
	elif [ "$INSTALL_OUT" = "dialog" ]; then
		dialog --backtitle "$SYSTEM_NAME" --title "$INSTALL_TITLE" --infobox "$1" 8 45 >&3
	else
		echo $1 >&3
	fi
	echo -e "\n==$1=="
}

ask()
{
	if [ "$INSTALL_OUT" = "vdr" ]; then
		kill $state_pid 2>/dev/null
		sleep 1
		svdrpsend.sh "PLUG apt ASK $1" | grep -q yes
		#send ask -s -t 10 "$1"
		#test $? -eq 2
		return $?
	elif [ "$INSTALL_OUT" = "webserver" -o "$ASSUME_YES" ]; then
		return 0
	elif [ "$INSTALL_OUT" = "dialog" ]; then
		dialog --clear --backtitle "$SYSTEM_NAME" --title "$INSTALL_TITLE" --yesno "$1" 8 45 >&3
		return $?
	else
		echo -n "$1 [Y/n] " >&3; read yn
		test "$yn" = "Y" -o "$yn" = "y" -o "$yn" = ""
		return $?
	fi
}

unmount()
{
	disk=$1
	mount | grep /dev/$disk | cut -d " " -f 1 | while read dev; do 
		echo -e "\n==unmount $dev=="
		umount $dev; 
	done
}

partitionDisk()
{
	parttype=$1
	disk=$2
	if [ $(($(grep " $disk$" /proc/partitions | sed "s/.* \(.*\) .*/\1/")/1024/1024/1024)) -ge 2 ]; then #"
		if [ -e /sbin/sgdisk ]; then
			gdisk=1
		else
			message "$(gt 'install gdisk')" 1
			lock apt -y install gdisk && gdisk=1
		fi
	fi
	message "$(gt 'partitioning disk $disk')" 1
	(
	set -e
	dd if=/dev/zero of=/dev/$disk bs=1024 count=1024
	case "$parttype" in
		single)
			# Nur eine Patition anlegen
			if [ "$gdisk" = "1" ]; then
				sgdisk -n 1 -c 1:Data -A 1:set:2 /dev/$disk
			else
				echo -e "n\np\n4\n\n+1K\n n\np\n1\n\n\n d\n4\n w" | fdisk -H 64 -S 32 /dev/$disk
			fi
			;;
		single_vfat)
			# Nur eine VFAT Patition anlegen
			echo -e "n\np\n4\n\n+1K\n n\np\n1\n\n\n d\n4\n t\nb\n w" | fdisk -H 64 -S 32 /dev/$disk
			;;
		system)
			if [ $(($(fdisk -l /dev/$disk | grep "^Disk" | head -n1 | cut -d " " -f 5)/1000000)) -lt 30000 ]; then
				echo -e "n\np\n4\n\n+1K\n n\np\n1\n\n\n d\n4\n a\n1\n w" | fdisk -H 64 -S 32 /dev/$disk
			else
				if [ "$gdisk" = "1" ]; then
					sgdisk -o /dev/$disk
					sgdisk -n 1::+10G -c 1:System -n 2 -c 2:Data -A 1:set:2 /dev/$disk
				else
					echo -e "n\np\n4\n\n+1K\n n\np\n1\n\n+10G\n n\np\n2\n\n\n d\n4\n a\n1\n w" | fdisk -H 64 -S 32 /dev/$disk
				fi
			fi
			;;
		system_with_vfat)
			if [ $(($(fdisk -l /dev/$disk | grep "^Disk" | head -n1 | cut -d " " -f 5)/1000000)) -lt 30000 ]; then
				echo -e "n\np\n4\n\n+1K\n n\np\n1\n\n+100M\n n\np\n2\n\n\n d\n4\n t\n1\nb\n w" | fdisk -H 64 -S 32 /dev/$disk
			else
				if [ "$gdisk" = "1" ]; then
					sgdisk -o /dev/$disk
					sgdisk -n 1::+100M -c 1:Boot -n 2::+10G -c 2:System -n 3 -c 3:Data -A 1:set:2 /dev/$disk
				else
					echo -e "n\np\n4\n\n+1K\n n\np\n1\n\n+100M\n n\np\n2\n\n+10G\n n\np\n3\n\n\n d\n4\n t\n1\nb\n w" | fdisk -H 64 -S 32 /dev/$disk
				fi
			fi
			;;
	esac
	hdparm -z /dev/$disk
	sleep 1
	fdisk -ul /dev/$disk
	); check_status
	return $?
}

formatDisk()
{
	disk=$1
	typ=$2
	message "$(gt 'format disk $disk')" 1
	(
	set -e
	umount /dev/$disk 2>/dev/null || true
	mkfs.$typ /dev/$disk $(test "$typ" = "xfs" && echo "-f"; test "$typ" = "btrfs" -a "$SYSTEM_TYPE" != "x86" && echo "-f -O ^extref")
	); check_status
	return $?
}

upgradeSystem()
{
	if [ "$INSTALLNET_COLLECTION" -a "$INSTALLNET_COLLECTION" != "none" ]; then
		message "$(gt 'upgrade system')" 1
		lock apt-get -y upgrade
		check_status
	fi
}

copySystem()
{
	system=$1
	data=${2-$1}
	leave_installer=$3
	message "$(gt 'install system on $system')" 1
	(
	set -e
	mkdir -p /mnt/$system
	mount /dev/$system /mnt/$system -o subvol=/
	if [ -e /mnt/$system/@root* ]; then
		echo "auto snapshot before install MLD-$SYSTEM_VERSION" > "/mnt/$system/@root*/snapshotinfo"
		mv /mnt/$system/@root* "/mnt/$system/$(date +"%Y-%m-%d %H:%M")"
	fi
	btrfs subvolume create /mnt/$system/@cache
	btrfs subvolume create /mnt/$system/@data
	btrfs subvolume create /mnt/$system/@root____________________
	btrfs subvolume set-default $(btrfs subvolume list /mnt/$system/ | grep " @root" | cut -d " " -f2) /mnt/$system
	
	cd /mnt/$system/@root*
	find / -maxdepth 1 ! -wholename / ! -name dev ! -name media ! -name mnt ! -name net ! -name proc ! -name sys -exec cp -a {} . ";"
	mkdir -p dev media mnt proc sys
	cp -a /dev/tty5 /dev/null dev

	rm -f var/spool/bootstep
	rm -f etc/udev/rules.d/00-no-auto-mount.rules

	if [ -e etc/vdr/menu.xml ]; then
		sed "/Install MiniDVBLinux/d" -i etc/vdr/menu.xml
	fi
	if [ -e etc/vdr/keymacros.conf ]; then
		sed "/User0 @setup 1/d" -i etc/vdr/keymacros.conf
	fi
	if ! grep -q "Backup" etc/setup/install.xml; then
		echo "<menu name=\"\$(""gt 'Backup')\">" > etc/setup/install.xml
		cat /etc/setup/install.xml >> etc/setup/install.xml
		echo "</menu>" >> etc/setup/install.xml
	fi
	rm -f var/www/tpl/navi/system/install.sh var/www/tpl/index/20_install.sh
	mkdir -p var/www/tpl/setup.d/Backup
	mv var/www/tpl/setup.d/0Install var/www/tpl/setup.d/Backup

	uuid=$(blkid /dev/$system -o value -s UUID)
	sed "/ \/ /d" -i etc/fstab
	echo "UUID=$uuid   /               auto   defaults             0 1" >> etc/fstab

	sed "/ \/var\/cache /d" -i etc/fstab
	echo "UUID=$uuid   /var/cache      btrfs  subvol=@cache        0 0" >> etc/fstab
	rm -rf var/cache/*
	mount /dev/$system var/cache -o subvol=@cache
	cp -a /var/cache/* var/cache
	umount var/cache

	uuid=$(blkid /dev/$data -o value -s UUID)
	sed "/\(^UUID=\|^\/dev\/\).* \/mnt\/data /d" -i etc/fstab
	mkdir -p mnt/data
	if [ "$data" = "$system" ]; then
		echo "UUID=$uuid   /mnt/data       btrfs  subvol=@data         0 0" >> etc/fstab
		mount /dev/$data mnt/data -o subvol=@data
	else
		echo "UUID=$uuid   /mnt/data       auto   defaults             0 2" >> etc/fstab
		mount /dev/$data mnt/data
	fi

	# copy data dir without mountet file systems
	(cd mnt/data; mkdir -p . $(ls -l /data/ | grep " -> " | sed "s|.* /mnt/data/||"))
	find /mnt -mount -path "/mnt/data/*" -type d -exec mkdir -p .{} \;
	find /mnt -mount -path "/mnt/data/*" ! -type d -exec cp -a {} .{} \;
	umount mnt/data

	cd /
	umount /mnt/$system
	); check_status
	return $?
}

writeBootFiles()
{
	type=$1
	shift
	if [ "$type" = "bpi" ]; then
		writeBootFilesBpi "$@"
	elif [ "$type" = "rpi" ]; then
		writeBootFilesRpi "$@"
	elif [ "$type" = "wtk" ]; then
		writeBootFilesWtk "$@"
	else
		writeBootFilesX86 "$@"
	fi
}

writeBootFilesBpi()
{
	system=$1
	boot=$2
	dev=$3
	message "$(gt 'write bootblock on $dev and boot files on $boot')" 1
	(
	set -e
	mkdir -p /mnt/$boot /mnt/$system
	mount /dev/$boot /mnt/$boot
	mount /dev/$system /mnt/$system

	rm -rf /mnt/$boot/* /mnt/$system/boot/*
	find /boot -maxdepth 1 -type f -exec cp -a {} /mnt/$boot ";"
	cp -r /boot/linux /mnt/$boot/ 2>/dev/null || true
	uuid=$(blkid /dev/$system -o value -s UUID)
	sed -e "s/bootargs=/bootargs=root=UUID=$uuid /" -i /mnt/$boot/uEnv.txt
	# mv /mnt/$boot/boot.scr_after_install /mnt/$boot/boot.scr
	cp -r /mnt/$boot/* /mnt/$system/boot

	uuid=$(blkid /dev/$boot -o value -s UUID)
	echo "UUID=$uuid   /boot        auto      defaults            0 2" >> /mnt/$system/etc/fstab

	umount /mnt/$system
	umount /mnt/$boot

	dd if=/boot/u-boot.bin of=/dev/$dev bs=1024 seek=8
	); check_status
	return $?
}

writeBootFilesRpi()
{
	system=$1
	boot=$2
	dev=$3
	message "$(gt 'write boot files on $boot')" 1
	(
	set -e
	mkdir -p /mnt/$boot /mnt/$system
	mount /dev/$boot /mnt/$boot
	mount /dev/$system /mnt/$system

	rm -rf /mnt/$boot/* /mnt/$system/boot/*
	find /boot -maxdepth 1 -type f -exec cp -a {} /mnt/$boot ";"
	if [ ${system%?} = ${boot%?} ]; then
		rootdev=mmcblk0p$(echo $system | sed "s/.*\(.\)/\1/") #"
	else
		rootdev=$system
	fi
	uuid=$(blkid /dev/$rootdev -o value -s UUID)
	{ echo -n "root=UUID=$uuid "; cat /boot/cmdline; } > /mnt/$boot/cmdline
	cp -r /mnt/$boot/* /mnt/$system/boot

	uuid=$(blkid /dev/$boot -o value -s UUID)
	echo "UUID=$uuid   /boot        auto      defaults            0 2" >> /mnt/$system/etc/fstab

	umount /mnt/$boot
	umount /mnt/$system
	); check_status
	return $?
}

writeBootFilesWtk()
{
	system=$1
	boot=$2
	dev=$3
	message "$(gt 'write boot files on $boot')" 1
	(
	set -e
	mkdir -p /mnt/$boot /mnt/$system
	mount /dev/$boot /mnt/$boot
	mount /dev/$system /mnt/$system

	rm -rf /mnt/$boot/* /mnt/$system/boot/*
	find /boot -maxdepth 1 -type f -exec cp -a {} /mnt/$boot ";"
	cp -r /mnt/$boot/* /mnt/$system/boot

	uuid=$(blkid /dev/$boot -o value -s UUID)
	echo "UUID=$uuid   /boot        auto      defaults            0 2" >> /mnt/$system/etc/fstab

	umount /mnt/$boot
	umount /mnt/$system
	); check_status
	return $?
}

writeBootFilesX86()
{
	system=$1
	boot=$2
	dev=$3
	message "$(gt 'write bootblock on $dev')" 1
	(
	set -e
	mkdir -p /mnt/$system
	mount /dev/$system /mnt/$system

	rm -f /mnt/$system/boot/syslinux/isolinux.* /mnt/$system/boot/syslinux/boot.cat
	cp /usr/lib/extlinux/vesamenu.c32 /mnt/$system/boot/syslinux/
	cat /usr/lib/extlinux/extlinux.conf > /mnt/$system/boot/syslinux/extlinux.conf
	uuid=$(blkid /dev/$system -o value -s UUID)
	sed -e "s|root=\S*|root=UUID=$uuid|g" -i /mnt/$system/boot/syslinux/extlinux.conf
	
	extlinux --install /mnt/$system/boot/syslinux
	if fdisk -l 2>/dev/null | grep /dev/$system | grep -q GPT; then
		cat /usr/lib/extlinux/gptmbr.bin > /dev/$dev
	else
		cat /usr/lib/extlinux/mbr.bin > /dev/$dev
	fi

	umount /mnt/$system
	); check_status
	return $?
}

automatic()
{
	sed "/^INSTALL_/d" -i /etc/rc.config
	
	if [ -z "$INSTALL_HDD" ]; then
		exit
	fi
	
	INSTALL_HDD=${INSTALL_HDD%%:*}
	
	if [ "$INSTALL_OUT" = "vdr" ]; then
		svdrpsend.sh HITK menu >/dev/null
	fi
	
	ask "$(gt 'install MLD on disk $INSTALL_HDD ?')" || exit
	message "$(gt 'start installation')"
	
	unmount $INSTALL_HDD
	
	if [ "$SYSTEM_TYPE" = "rpi" -o "$SYSTEM_TYPE" = "bpi" -o "$SYSTEM_TYPE" = "wtk" ]; then
		partitionDisk system_with_vfat $INSTALL_HDD || exit 1
		boot=$(cd /dev; ls $INSTALL_HDD*1)
		system=$(cd /dev; ls $INSTALL_HDD*2)
		data=$(cd /dev; ls $INSTALL_HDD*3 2>/dev/null || ls $INSTALL_HDD*2)
	else
		partitionDisk system $INSTALL_HDD || exit 1
		boot=
		system=$(cd /dev; ls $INSTALL_HDD*1)
		data=$(cd /dev; ls $INSTALL_HDD*2 2>/dev/null || ls $INSTALL_HDD*1)
	fi
	
	sleep 1
	unmount $INSTALL_HDD
	if [ "$boot" ]; then
		formatDisk $boot vfat || exit 2
	fi
	formatDisk $system btrfs || exit 2
	if [ "$data" != "$system" ]; then
		formatDisk $data xfs || exit 3
	fi
	
	upgradeSystem
	
	copySystem $system $data $INSTALL_LEAVE_INSTALLER || exit 4
	
	writeBootFiles "$SYSTEM_TYPE" "$system" "$boot" "$INSTALL_HDD" || exit 5
	
	
	if [ "$INSTALL_OUT" = "webserver" -o "$ASSUME_YES" ]; then
		info "$(gt 'Installation completed')"
	else
		bootdev=$(readlink /dev/boot 2>/dev/null || readlink /dev/root 2>/dev/null)
		if [ "${bootdev##/dev/$INSTALL_HDD*}" ]; then
			ask "$(gt 'Installation completed. Remove the CD. Boot installed system now?')" || exit
		else
			ask "$(gt 'Installation completed. Boot installed system now?')" || exit
		fi
		reboot
	fi
}

manual()
{
	sed "/^INSTALL_/d" -i /etc/rc.config
	
	INSTALL_HDD=${INSTALL_HDD%%:*}
	INSTALL_SYSTEM_PARTITION=${INSTALL_SYSTEM_PARTITION%%:*}
	INSTALL_DATA_PARTITION=${INSTALL_DATA_PARTITION%%:*}
	INSTALL_BOOT_PARTITION=${INSTALL_BOOT_PARTITION%%:*}
	INSTALL_BOOTBLOCK_HDD=${INSTALL_BOOTBLOCK_HDD%%:*}

	if [ "$INSTALL_OUT" = "vdr" ]; then
		svdrpsend.sh HITK menu >/dev/null
	fi
	
    if [ "$INSTALL_SYSTEM_PARTITION" = "$INSTALL_BOOT_PARTITION" -a "$INSTALL_BOOT_PARTITION" ]; then
        message "$(gt 'Boot and system partition must not be the same')"
        return 1
    fi
#	ask "$(gt 'install MLD on selected disk ?')" || exit
	message "$(gt 'start installation')"
	
	if [ -n "$INSTALL_HDD" ]; then
		ask "$(gt 'Partition disk $INSTALL_HDD ?')"
		if [ $? -eq 0 ]; then
			unmount $INSTALL_HDD
			if [ "$SYSTEM_TYPE" = "rpi" -o "$SYSTEM_TYPE" = "bpi" -o "$SYSTEM_TYPE" = "wtk" ]; then
				partitionDisk system_with_vfat $INSTALL_HDD || exit 1
				boot=$(cd /dev; ls $INSTALL_HDD*1)
				system=$(cd /dev; ls $INSTALL_HDD*2)
				data=$(cd /dev; ls $INSTALL_HDD*3 2>/dev/null || ls $INSTALL_HDD*2)
			else
				partitionDisk system $INSTALL_HDD || exit 1
				boot=$(cd /dev; ls $INSTALL_HDD*1)
				system=$(cd /dev; ls $INSTALL_HDD*1)
				data=$(cd /dev; ls $INSTALL_HDD*2 2>/dev/null || ls $INSTALL_HDD*1)
			fi
			INSTALL_BOOT_PARTITION=$boot
			INSTALL_SYSTEM_PARTITION=$system
			INSTALL_DATA_PARTITION=$data
			INSTALL_FORMAT_BOOT_PARTITION=1
			INSTALL_FORMAT_SYSTEM_PARTITION=1
			INSTALL_FORMAT_DATA_PARTITION=1
		fi
	fi
	
	if [ "$INSTALL_FORMAT_BOOT_PARTITION" = 1 -a -n "$INSTALL_BOOT_PARTITION" ]; then
		ask "$(gt 'Format boot partition $INSTALL_BOOT_PARTITION ?')"
		if [ $? -eq 0 ]; then
			unmount $INSTALL_BOOT_PARTITION
			if [ -z "${INSTALL_BOOT_PARTITION#mmcblk?}" ]; then
				partitionDisk single_vfat $INSTALL_BOOT_PARTITION || exit 1
				INSTALL_BOOT_PARTITION=$(cd /dev; ls $INSTALL_BOOT_PARTITION*1)
			fi
			formatDisk $INSTALL_BOOT_PARTITION vfat || exit 2
		fi
	fi
 	if [ "$INSTALL_FORMAT_SYSTEM_PARTITION" = 1 -a -n "$INSTALL_SYSTEM_PARTITION" ]; then
		ask "$(gt 'Format system partition $INSTALL_SYSTEM_PARTITION ?')"
		if [ $? -eq 0 ]; then
			unmount $INSTALL_SYSTEM_PARTITION
			if [ -z "${INSTALL_SYSTEM_PARTITION#sd?}" ]; then
				partitionDisk single $INSTALL_SYSTEM_PARTITION || exit 1
				if [ "$INSTALL_DATA_PARTITION" = "$INSTALL_SYSTEM_PARTITION" ]; then
					INSTALL_DATA_PARTITION=$(cd /dev; ls $INSTALL_DATA_PARTITION*1)
				fi
				INSTALL_SYSTEM_PARTITION=$(cd /dev; ls $INSTALL_DATA_PARTITION*1)
			fi
			formatDisk $INSTALL_SYSTEM_PARTITION btrfs || exit 2
		fi
	fi
	if [ "$INSTALL_FORMAT_DATA_PARTITION" = 1 -a -n "$INSTALL_DATA_PARTITION" -a "$INSTALL_DATA_PARTITION" != "$INSTALL_SYSTEM_PARTITION" ]; then
		ask "$(gt 'Format data partition $INSTALL_DATA_PARTITION ?')"
		if [ $? -eq 0 ]; then
			unmount $INSTALL_DATA_PARTITION
			if [ -z "${INSTALL_DATA_PARTITION#sd?}" -a "$INSTALL_DATA_PARTITION" != "${INSTALL_SYSTEM_PARTITION%?}" ]; then
				partitionDisk single $INSTALL_DATA_PARTITION || exit 1
				INSTALL_DATA_PARTITION=$(cd /dev; ls $INSTALL_DATA_PARTITION*1)
			fi
			formatDisk $INSTALL_DATA_PARTITION xfs || exit 3
		fi
	fi
	
	if [ "$INSTALL_COPY_SYSTEM" = 1 -a -n "$INSTALL_SYSTEM_PARTITION" ]; then
		upgradeSystem
		
		copySystem "$INSTALL_SYSTEM_PARTITION" "${INSTALL_DATA_PARTITION:-$INSTALL_SYSTEM_PARTITION}" $INSTALL_LEAVE_INSTALLER || exit 4
	fi
	
	if [ "$INSTALL_BOOTBLOCK_HDD" = "auto" ]; then
		INSTALL_BOOTBLOCK_HDD=$INSTALL_HDD
	fi

	if [ "$INSTALL_COPY_BOOT" = 1 -a -n "$INSTALL_BOOT_PARTITION" -a -n "$INSTALL_SYSTEM_PARTITION" ] || [ -n "$INSTALL_BOOTBLOCK_HDD" -a -n "$INSTALL_SYSTEM_PARTITION" ]; then
		INSTALL_BOOTBLOCK_HDD=${INSTALL_BOOTBLOCK_HDD%%:*}
		writeBootFiles "$SYSTEM_TYPE" "$INSTALL_SYSTEM_PARTITION" "$INSTALL_BOOT_PARTITION" "$INSTALL_BOOTBLOCK_HDD" || exit 5
	fi
	
	info "$(gt 'Installation completed')"
}


INSTALL_OUT=${2-sh}
INSTALL_TITLE=$3
status=0

# disable the automounter
echo 'ACTION=="add|change", SUBSYSTEM=="block", ENV{UDISKS_AUTOMOUNT_HINT}="never"' > /etc/udev/rules.d/00-no-auto-mount.rules
udevadm control --reload-rules
sleep 1

case $1 in
	automatic)
		if [ "$INSTALL_OUT" = "vdr" ]; then
			automatic 1>/var/log/install.log 2>&1 </dev/null &
		else
			exec 3>&1
			automatic 1>/var/log/install.log 2>&1; status=$?
			exec 3>&-
		fi
		;;
	manual)
		if [ "$INSTALL_OUT" = "vdr" ]; then
			manual 1>/var/log/install.log 2>&1 </dev/null &
		else
			exec 3>&1
			manual 1>/var/log/install.log 2>&1; status=$?
			exec 3>&-
		fi
		;;
esac

# enable the automounter
rm /etc/udev/rules.d/00-no-auto-mount.rules
udevadm control --reload-rules

exit $status
