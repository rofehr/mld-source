# in dem MLD-netinstall image wurden ursprünglich folgende Pakete benötigt. Vernachlässigt werden könnten für den 1.Start die Plugins: locales backup live webserver
# netinstall        = locales backup install install-net live webserver ssh
# base              = xfs extlinux busybox initramfs
SUMMARY = "A MLD base image ."
DESCRIPTION = "An image containing the BASE system for MLD ${DISTRO_VERSION} "
HOMEPAGE = "http://www.minidvblinux.de"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

#COMPRESSISO = "1"


LABELS = "MLD"
INITRD_IMAGE = "MLD-initramfs"
INITRAMFS_IMAGE_BUNDLE = "1"
IMAGE_FSTYPES = "cpio.gz"
INITRD = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE}-${MACHINE}.cpio.gz"

#APPEND = "console=ttyS0,115200 console=ttyPCH0,115200"
#SPLASH_IMAGE = "silent.png"
#GRUB_TIMEOUT = "2" 
  
inherit bootimg

do_bootimg[depends] += "${INITRD_IMAGE}:do_build"
do_bootimg[depends] += "virtual/kernel:do_populate_sysroot"

addtask do_bootimg before do_build
addtask do_unpack before do_build

# Roor Passwort setzen :)
#inherit extrausers 
#EXTRA_USERS_PARAMS = "usermod -P mld600 root;"

export IMAGE_BASENAME="MLD-Base-image"



