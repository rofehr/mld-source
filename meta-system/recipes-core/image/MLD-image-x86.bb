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

#inherit core-image
#inherit image
include recipes-core/images/core-image-minimal.bb


PACKAGE_ARCH = "${MACHINE_ARCH}"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
              
GLIBC_GENERATE_LOCALES = "de_DE.UTF-8"
IMAGE_LINGUAS ?= "de-de"

LABELS = "MLD"


IMAGE_FEATURES += "package-management ssh-server-openssh splash"


#IMAGE_INSTALL += " \
#    kernel-modules\
#    base-files \
#    base \
#    findutils \
#    init-mld \
#    gettext \
#    gettext-runtime \
#    urldecode \
#    webserver \
#    extlinux \
#    psplash \
#    ssh-mld \
#    udev-mld \
#    install \
#    locales \
#    "    



#INITRAMFS_IMAGE = "MLD-initramfs"
#INITRAMFS_IMAGE_BUNDLE = "1"

#INITRD_IMAGE = "MLD-initramfs"
#INITRD_IMAGE = "initramfs-framework"
#IMAGE_FSTYPES = "iso"

#VIRTUAL-RUNTIME_login_manager = "busybox"
#VIRTUAL-RUNTIME_init_manager = "busybox"
#VIRTUAL-RUNTIME_initscripts = "init"


#IMAGE_INSTALL = " "
#ROOTFS = " "

# Alle Einstellungen die sich auf das Syslinux beziehen
AUTO_SYSLINUXMENU = "1"
#SYSLINUX_SPLASH = "${WORKDIR}/silent.png"
#SYSLINUX_ROOT = "root=/"
#APPEND = "vga=0x314 splash=verbose nobg apm=off"

export IMAGE_BASENAME="x86"

