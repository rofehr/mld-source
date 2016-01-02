# Simple initramfs image for the MLD Distro
DESCRIPTION = "Simple initramfs image for the MLD Distro."


PACKAGE_INSTALL = " packagegroup-mld-base \
    				packagegroup-mld-cmdline \
    				packagegroup-mld-install \
"


# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
IMAGE_FEATURES += "debug-tweaks"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

BAD_RECOMMENDATIONS += "busybox-syslog"

export IMAGE_BASENAME = "MLD-initramfs"

