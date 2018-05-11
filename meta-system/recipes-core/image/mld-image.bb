include recipes-core/images/core-image-minimal.bb

INITRAMFS_MAXSIZE ??= "262144"

IMAGE_LINGUAS = "de-de"
LABELS = "MLD"

LICENSE = "MIT"

SPLASH = "psplash"

base_sbindir_progs = "True"
base_bindir_progs = "True"


IMAGE_FEATURES += "package-management ssh-server-openssh splash"


PACKAGE_INSTALL_append += " \
    kernel-modules\
    base-files \
    base \
    findutils \
    init-mld \
    gettext \
    gettext-runtime \
    urldecode \
    webserver \
    extlinux \
    psplash \
    ssh-mld \
    udev-mld \
    install \
    locales \
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
"

export IMAGE_BASENAME="mld-image"

#COMPATIBLE_HOST = "(i.86|x86_64|raspberrypi|raspberrypi2|raspberrypi3)"