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

PACKAGE_ARCH = "${MACHINE_ARCH}"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
              
#IMAGE_LINGUAS = " "
#LABELS = "MLD"

SPLASH = "psplash"

IMAGE_FEATURES += " package-management \
                    ssh-server-openssh \
                    splash \
                    "

    
IMAGE_INSTALL += " ethtool ifupdown e2fsprogs udev udev-mld init-mld network webserver \
                   base findutils busybox nano mc gettext gettext-runtime kernel-modules \
                   apt apt-mld dpkg-mld bash util-linux-blkid btrfs-tools ffmpeg"    
    
#IMAGE_INSTALL += " \
#    packagegroup-mld-base \
#    packagegroup-mld-network \
#    packagegroup-mld-output-device-rpi \
#    packagegroup-mld-satip-client \
#"

#INITRD_IMAGE = "mld-initramfs-rpi"
KERNEL_INITRAMFS = ""

VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "init"

SYSLINUX_ROOT = "root=/dev/mmcblk0p2"

#IMAGE_INSTALL = " "
ROOTFS = " "

#AUTO_SYSLINUXMENU = "1"

include recipes-core/images/core-image-minimal.bb

export IMAGE_BASENAME="MLD-image"

