DESCRIPTION = "MLD base Package"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup 

RDEPENDS__${PN} = " \
    busybox \
    udev \
    initscripts \
    sysvinit \
    acl \
    attr \
    nfs-utils \
    xfsprogs \
    btrfs-tools \
    dialog \
    findutils \
    gptfdisk \
    gettext \
    gettext-runtime \
    ssh \
    hdparm \
    modutils-initscripts \
    base \
    psplash \
"
