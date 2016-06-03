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
              
#IMAGE_LINGUAS = " "
LABELS = "MLD"

INITRD_IMAGE = "mld-initramfs"

VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "init"


IMAGE_INSTALL = " "
ROOTFS = " "

AUTO_SYSLINUXMENU = "1"
#SYSLINUX_ROOT = "root=/dev/mmcblk0p2"

#SYSLINUX_SPLASH = "${WORKDIR}/silent.png"
#APPEND = "vga=0x314 splash=verbose nobg apm=off"

#inherit core-image
inherit image

export IMAGE_BASENAME="rpi"

