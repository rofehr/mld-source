# Simple initramfs image. Mostly used for live images.
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."

#PACKAGE_INSTALL = " udev-mld init-mld network webserver base findutils busybox nano mc ethtool ifupdown gettext gettext-runtime kernel-modules \
#                    apt apt-mld dpkg-mld bash udev util-linux-blkid btrfs-tools"

PACKAGE_INSTALL = " ethtool ifupdown e2fsprogs udev udev-mld init-mld network webserver base findutils busybox nano mc gettext gettext-runtime kernel-modules \
                    apt apt-mld dpkg-mld bash util-linux-blkid btrfs-tools"


# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "core-image-minimal-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

