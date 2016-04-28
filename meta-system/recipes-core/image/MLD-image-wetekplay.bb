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

# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

#IMAGE_INSTALL += "libamcodec vdr vdr-plugin-amlhddevice vdr-font-symbols "
 
IMAGE_INSTALL += "kernel-modules alsa-utils nano mc gettext gettext-runtime"

#MLD-STUFF
IMAGE_INSTALL += " init-mld dpkg-mld udev-mld apt apt-mld network webserver base findutils \
                   udev-mld busybox nano mc ethtool ifupdown gettext gettext-runtime kernel-modules \
                   bash udev"


IMAGE_FEATURES += "splash package-management ssh-server-dropbear "

export IMAGE_BASENAME="wtk"
