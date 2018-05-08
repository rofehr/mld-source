#PACKAGE_INSTALL = "${IMAGE_INSTALL} urldecode ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                    base findutils busybox gettext gettext-runtime kernel-modules \
#                    apt apt-mld dpkg-mld util-linux-blkid install btrfs-tools gptfdisk \
#                    syslinux extlinux initramfs ssh-mld dropbear "

SPLASH = "psplash"

#PACKAGE_INSTALL = "${IMAGE_INSTALL} urldecode ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                    base findutils busybox gettext gettext-runtime kernel-modules \
#                    apt apt-mld dpkg-mld util-linux-blkid install btrfs-tools gptfdisk \
#                    syslinux extlinux ssh-mld dropbear net-tools"

#IMAGE_INSTALL += " base-files urldecode ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                    base findutils busybox gettext gettext-runtime kernel-modules \
#                    apt apt-mld dpkg-mld util-linux-blkid install btrfs-tools gptfdisk \
#                    syslinux extlinux ssh-mld dropbear net-tools"
                  
#PACKAGE_INSTALL = "${IMAGE_INSTALL} urldecode ifupdown e2fsprogs udev udev-mld init-mld network webserver \
#                    base findutils busybox gettext gettext-runtime kernel-modules \
#                    apt apt-mld dpkg-mld util-linux-blkid install btrfs-tools gptfdisk \
#                    syslinux extlinux ssh-mld dropbear net-tools"

PACKAGE_INSTALL = "${IMAGE_INSTALL} "



GLIBC_GENERATE_LOCALES = "de_DE.UTF-8"
IMAGE_LINGUAS = "de-de"


IMAGE_FSTYPES = "iso"

INHERIT += "rm_work"
 
export IMAGE_BASENAME="mld-base-image"
