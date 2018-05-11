##############################################################
#
#  conten: mld-base-image
#  arch:   x86  
#   
##############################################################
SUMMARY = "A MLD base image ."

DESCRIPTION = "An image containing the BASE system for MLD ${DISTRO_VERSION} "

HOMEPAGE = "http://www.minidvblinux.de"

LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
              
GLIBC_GENERATE_LOCALES = "de_DE.UTF-8"
IMAGE_LINGUAS ?= "de-de"

LABELS = "MLD"

#INITRD_IMAGE = "mld-initramfs-bpi"

#VIRTUAL-RUNTIME_login_manager = "busybox"
#VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "init"

#SYSLINUX_ROOT = "root=/dev/mmcblk0p2"

# Base Packages
#IMAGE_INSTALL += " urldecode ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                   base findutils busybox gettext gettext-runtime kernel-modules \
#                   apt apt-mld dpkg-mld util-linux-blkid install btrfs-tools gptfdisk \
#                   syslinux extlinux initramfs ssh-mld dropbear "
                    
                    
IMAGE_INSTALL = " urldecode ifupdown e2fsprogs udev udev-mld init-mld network webserver \
                  base findutils busybox gettext gettext-runtime kernel-modules \
                   apt apt-mld dpkg-mld util-linux-blkid install btrfs-tools gptfdisk \
                   syslinux extlinux initramfs ssh-mld dropbear "
                   
 
#Only for Testing
IMAGE_INSTALL += " nano mc"

#IMAGE_INSTALL = " "
ROOTFS = "root=/dev/mmcblk0p2"

AUTO_SYSLINUXMENU = "1"
#SYSLINUX_SPLASH = "${WORKDIR}/silent.png"
#APPEND = "vga=0x314 splash=verbose nobg apm=off"

#inherit core-image
#inherit image
include recipes-core/images/core-image-minimal.bb

export IMAGE_BASENAME="bpi"

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"