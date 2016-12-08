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

INITRD_IMAGE = "mld-initramfs-vdr-x86"
IMAGE_FSTYPES = "iso"

VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "init"

IMAGE_FEATURES = " "

IMAGE_INSTALL = " "
ROOTFS = " "

# Alle Einstellungen die sich auf das Syslinux beziehen
AUTO_SYSLINUXMENU = "1"
#SYSLINUX_SPLASH = "${WORKDIR}/silent.png"
SYSLINUX_ROOT = "root=/"
APPEND = "vga=0x314 splash=verbose nobg apm=off"


#inherit core-image
inherit image
#include recipes-core/images/core-image-minimal.bb

export IMAGE_BASENAME="x86"

