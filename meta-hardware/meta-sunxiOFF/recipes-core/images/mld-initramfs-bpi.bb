# Simple initramfs image. Mostly used for live images.
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."


# Base Packages
PACKAGE_INSTALL = " urldecode ifupdown e2fsprogs udev udev-mld init-mld network webserver \
                    base findutils busybox gettext gettext-runtime kernel-modules \
                    apt apt-mld dpkg-mld util-linux-blkid install btrfs-tools gptfdisk \
                    syslinux extlinux initramfs ssh-mld dropbear "
                   
                    
PACKAGE_INSTALL += "  "
 
#Only for Testing
PACKAGE_INSTALL += " nano mc"

# Do not pollute the initrd image with rootfs features
#IMAGE_FEATURES = "package-management"
IMAGE_FEATURES = " "
IMAGE_LINGUAS = "de-de"


LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

#inherit core-image
#inherit image
include recipes-core/images/core-image-minimal.bb


IMAGE_ROOTFS_SIZE = "8192"

export IMAGE_BASENAME = "mld-initramfs-bpi"

