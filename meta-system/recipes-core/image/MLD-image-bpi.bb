##############################################################
#
#  conten: mld-base-image
#  arch:   bpi  
#   
##############################################################

SUMMARY = "A MLD base image ."

DESCRIPTION = "An image containing the BASE system for MLD ${DISTRO_VERSION} "

HOMEPAGE = "http://www.minidvblinux.de"

LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
                 
LABELS = "MLD"

SPLASH_IMAGE = "silent.png"

INITRD_IMAGE = "core-image-minimal-initramfs"

SYSLINUX_ROOT = "root=/dev/mmcblk0p2"

#DISTRO_FEATURES += "systemd"

VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "init"




#IMAGE_INSTALL += " ethtool ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                   base findutils busybox nano mc gettext gettext-runtime kernel-modules \
#                   apt apt-mld dpkg-mld bash util-linux-blkid btrfs-tools "


#IMAGE_INSTALL += " modutils-initscripts init-ifupdown"

#IMAGE_INSTALL += " packagegroup-core-eclipse-debug"

                 
#IMAGE_FEATURES += " package-management ssh-server-dropbear"


IMAGE_FEATURES = " ssh-server-dropbear"

ROOTFS = " "

inherit core-image

export IMAGE_BASENAME="bpi"

