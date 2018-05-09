include recipes-core/images/core-image-minimal.bb

KERNEL_INITRAMFS = ""

#INITRAMFS_IMAGE = "mld-image"
#INITRAMFS_IMAGE_BUNDLE = "1"
#IMAGE_FSTYPES = "cpio.gz"

IMAGE_LINGUAS = "de-de"
LABELS = "MLD"

SPLASH = "psplash"

base_sbindir_progs = "True"
base_bindir_progs = "True"

IMAGE_FEATURES += "package-management ssh-server-openssh splash"



    
## MLD-Stuff
IMAGE_INSTALL += " \
    base \
    findutils \
    init-mld \
    initramfs \
    install \
    locales \
    network \
    ssh-mld \
    udev-mld \
    urldecode \
    webserver \
    gettext \
    gettext-runtime \
    "	
    



## Wetekplay specially
RRECOMMENDS_${PN}_append_wetekplay = " \
    wetek-dvb-modules \
    amlsetfb \
"

## bananapi specially
RRECOMMENDS_${PN}_append_bpi = " \
    sunxi-mali \
    libvdpau-sunxi \
"

RRECOMMENDS_${PN}_append_bpi += " \
    gstreamer1.0 \
    vdr-font-symbols \
    vdr \
    vdr-locale-de-de \
"

	
## cubietruck specially
RRECOMMENDS_${PN}_append_cubietruck = " \
    sunxi-mali \
    libvdpau-sunxi \
"

export IMAGE_BASENAME="mld-image"

