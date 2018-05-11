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

include recipes-core/images/core-image-minimal.bb

              
#IMAGE_LINGUAS = " "
#LABELS = "MLD"

SPLASH = "psplash"

IMAGE_FEATURES += " package-management ssh-server-openssh splash "

IMAGE_INSTALL += " \
    kernel-modules \
    cpufrequtils \
    procps \
    util-linux \
    nfs-utils \
    tzdata \
    ttf-bitstream-vera \
    font-opensans \
    irmplircd \
    graphlcd-base-ax206dpf \
    vdr-font-symbols \
    vdr \
    vdr-locale-de-de \
    vdr-plugin-mlist \
    vdr-plugin-mlist-locale-de-de \
    vdr-plugin-femon \
    vdr-plugin-femon-locale-de-de \
    vdr-plugin-satip \
    vdr-plugin-satip-locale-de-de \
    vdr-plugin-graphlcd-ax206dpf \
    vdr-plugin-graphlcd-ax206dpf-locale-de-de \
    vdr-plugin-svdrpservice \
    vdr-plugin-svdrpservice-locale-de-de \
    vdr-plugin-remotetimers \
    vdr-plugin-remotetimers-locale-de-de \
    vdr-plugin-epgsync \
    vdr-plugin-epgsync-locale-de-de \
    vdr-plugin-neutrinoepg \
    vdr-plugin-neutrinoepg-locale-de-de \
    vdr-plugin-skinsoppalusikka \
    vdr-plugin-skinsoppalusikka-locale-de-de \
    vdr-plugin-skinenigmang \
    vdr-plugin-skinenigmang-locale-de-de \
    vdr-plugin-skinnopacity \
    vdr-plugin-skinnopacity-locale-de-de \
    vdr-plugin-skinpearlhd \
    vdr-plugin-skinpearlhd-locale-de-de \
    vdr-plugin-skindesigner \
    vdr-plugin-skindesigner-locale-de-de \
    vdr-plugin-streamdev \
    vdr-plugin-streamdev-locale-de-de \
    vdr-plugin-extrecmenu \
    vdr-plugin-extrecmenu-locale-de-de \
    vdr-plugin-remote \
    vdr-plugin-remote-locale-de-de \
    vdr-plugin-skinenigmang-logos-xpm-hi"
    
IMAGE_INSTALL += " glibc-utils \
    glibc-binaries \
    glibc-binary-localedata-de-de \
    glibc-localedata-de-de \
    locale-base-de-de \
    localedef \
    glibc-charmap-iso-8859-1 \
    glibc-charmap-iso-8859-15 \
    glibc-charmap-utf-8 \
    glibc-gconv-iso8859-1 \
    glibc-gconv-iso8859-15 \
    glibc-gconv-unicode "
    
IMAGE_INSTALL += " \
    keymaps \
    kbd \
    nano \
    mc "  

#MLD 
IMAGE_INSTALL += " \
    busybox \
    findutils \
    base \
    init-mld \
    gettext \
    gettext-runtime \
    ifupdown \
    e2fsprogs \
    udev \
    udev-mld \
    network \
    webserver \
    bash \
    util-linux-blkid \
    btrfs-tools \
    urldecode \
    net-tools \
"  

# RPI only
IMAGE_INSTALL += " \
    libcec-rpi \
    vdr-plugin-rpihddevice \
    vdr-plugin-rpihddevice-locale-de-de \
    vdr-plugin-cecremote \
"

#IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

#INITRD_IMAGE = "mld-initramfs-rpi"
#KERNEL_INITRAMFS = "-initramfs"

VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "init"

SYSLINUX_ROOT = "root=/dev/mmcblk0p2"

#IMAGE_INSTALL = " "
#ROOTFS = " "

#AUTO_SYSLINUXMENU = "1"

export IMAGE_BASENAME="MLD-image"

