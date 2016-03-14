##############################################################
#
#  conten: mld-base-image
#  arch:   raspberrypi  
#   
##############################################################


# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

SUMMARY = "A MLD base image ."
DESCRIPTION = "An image containing the BASE system for MLD ${DISTRO_VERSION} "
HOMEPAGE = "http://www.minidvblinux.de"

LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
                    


LABELS = "MLD"
SPLASH_IMAGE = "silent.png"


IMAGE_FEATURES += "ssh-server-dropbear"

export IMAGE_BASENAME="MLD-Base-image"
