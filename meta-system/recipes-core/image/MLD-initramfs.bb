# Simple initramfs image for the MLD Distro
DESCRIPTION = "Simple initramfs image for the MLD Distro."

GLIBC_GENERATE_LOCALES = "de_DE.UTF-8"

IMAGE_LINGUAS ?= "de-de"

INITRAMFS_MAXSIZE ??= "262144"

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


# Do not pollute the initrd image with rootfs features
#IMAGE_FEATURES = ""


LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
#IMAGE_FEATURES += "debug-tweaks"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

BAD_RECOMMENDATIONS += "busybox-syslog"

export IMAGE_BASENAME = "MLD-initramfs"

