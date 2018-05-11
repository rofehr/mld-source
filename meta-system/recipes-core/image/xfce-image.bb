DESCRIPTION = "A XFCE minimal demo image."

IMAGE_INSTALL = "packagegroup-core-boot \
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    packagegroup-core-x11-utils \
    xserver-nodm-init \
    x11-common \
    xserver-common \
"

REQUIRED_DISTRO_FEATURES = "x11"

IMAGE_LINGUAS ?= " "

LICENSE = "MIT"

export IMAGE_BASENAME = "xfce-image"

inherit core-image

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"