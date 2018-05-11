include recipes-core/images/core-image-minimal.bb

IMAGE_LINGUAS = "de-de"
LABELS = "MLD"

LICENSE = "MIT"

SPLASH = "psplash"

base_sbindir_progs = "True"
base_bindir_progs = "True"


IMAGE_FEATURES += "package-management ssh-server-openssh splash"

PACKAGE_INSTALL_append += " \
    kernel-modules\
    base \
    init-mld \
    gettext \
    gettext-runtime \
    webserver \
    "    

#PACKAGE_INSTALL_append += " \
#    kernel-modules\
#    base \
#    findutils \
#    init-mld \
#    install \
#    locales \
#    network \
#    ssh-mld \
#    udev-mld \
#    urldecode \
#    webserver \
#    gettext \
#    gettext-runtime \
#    init-ifupdown \
#    network \
#    apt \
#    "    




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

