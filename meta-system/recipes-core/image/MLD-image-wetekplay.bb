##############################################################
#
#  conten: mld-base-image
#  arch:   bananapi  
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


SYSLINUX_ROOT = "root=/"

DISTRO_FEATURES += "systemd"

VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "init"

# Base this image on core-image-minimal
#include recipes-core/images/core-image-minimal.bb

 
IMAGE_INSTALL += " initramfs-boot-am "
IMAGE_INSTALL += " kernel-modules alsa-utils nano mc gettext gettext-runtime"


IMAGE_INSTALL += " ethtool ifupdown e2fsprogs udev udev-mld init-mld network webserver \
                   base findutils busybox nano mc gettext gettext-runtime kernel-modules \
                   apt apt-mld dpkg-mld bash util-linux-blkid btrfs-tools"
             
IMAGE_INSTALL += " libamcodec vdr vdr-plugin-amlhddevice"


IMAGE_FEATURES += " splash package-management ssh-server-dropbear "

inherit core-image

export IMAGE_BASENAME="wtk"
