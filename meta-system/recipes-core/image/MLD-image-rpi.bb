##############################################################
#
#  conten: mld-base-image
#  arch:   rpi  
#   
##############################################################

SUMMARY = "A MLD base image ."

DESCRIPTION = "An image containing the BASE system for MLD ${DISTRO_VERSION} "

HOMEPAGE = "http://www.minidvblinux.de"

LICENSE = "MIT"

include recipes-core/images/core-image-minimal.bb

PACKAGE_ARCH = "${MACHINE_ARCH}"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
              
GLIBC_GENERATE_LOCALES = "de_DE.UTF-8"
IMAGE_LINGUAS ?= "de-de"
                 
LABELS = "MLD"

SPLASH_IMAGE = "silent.png"

IMAGE_FEATURES += " package-management ssh-server-dropbear splash"

IMAGE_INSTALL_append += " \
    kernel-modules\
    base-files \
    base \
    findutils \
    init-mld \
    gettext \
    gettext-runtime \
    urldecode \
    webserver \
    extlinux \
    psplash \
    ssh-mld \
    udev-mld \
    install \
    locales \
    "    
    
# Base this image on core-image-minimal
#include recipes-core/images/core-image-minimal.bb

SYSLINUX_ROOT = "root=/dev/mmcblk0p2"

#DISTRO_FEATURES += "systemd"

#VIRTUAL-RUNTIME_login_manager = "busybox"
#VIRTUAL-RUNTIME_init_manager = "busybox"
#VIRTUAL-RUNTIME_initscripts = "init"


#IMAGE_DEV_MANAGER   = "udev"
#IMAGE_INIT_MANAGER  = "systemd"
#IMAGE_INITSCRIPTS   = "init"
#IMAGE_LOGIN_MANAGER = "busybox"


#IMAGE_INSTALL += " ethtool ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                   base findutils busybox nano mc gettext gettext-runtime kernel-modules \
#                   apt apt-mld dpkg-mld bash util-linux-blkid btrfs-tools "
                  

#inherit core-image

export IMAGE_BASENAME="rpi"
#COMPATIBLE_HOST = "(raspberrypi |raspberrypi2 |raspberrypi3)"