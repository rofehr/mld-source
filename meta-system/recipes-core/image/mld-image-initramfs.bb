# Simple initramfs image. Mostly used for live images.
DESCRIPTION = ""

INITRAMFS_MAXSIZE ??= "262144"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "mld-image-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

BAD_RECOMMENDATIONS += "busybox-syslog"

# Use the same restriction as initramfs-live-install
COMPATIBLE_HOST = "(i.86|x86_64).*-linux"



