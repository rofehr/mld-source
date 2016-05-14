# Simple initramfs image. Mostly used for live images.
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."


#PACKAGE_INSTALL = " ethtool ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                    base findutils busybox nano mc gettext gettext-runtime kernel-modules \
#                    apt apt-mld dpkg-mld bash util-linux-blkid btrfs-tools"

PACKAGE_INSTALL = " ethtool ifupdown e2fsprogs udev udev-mld init-mld network webserver \
                    base findutils busybox nano mc gettext gettext-runtime kernel-modules \
                    apt apt-mld util-linux-blkid "
                    
#PACKAGE_INSTALL = " ssh-server-dropbear"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = " "

IMAGE_LINGUAS = " de-de"

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

#inherit core-image
inherit image

IMAGE_ROOTFS_SIZE = "8192"

export IMAGE_BASENAME = "mld-initramfs"
