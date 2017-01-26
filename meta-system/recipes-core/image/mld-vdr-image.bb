include recipes-core/images/core-image-minimal.bb

SPLASH = "psplash"

IMAGE_FEATURES += "package-management ssh-server-openssh splash"

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
    nano "	

#MLD 
IMAGE_INSTALL += " \
    init-mld \
    gettext \
"  

## raspberry specially
RRECOMMENDS_${PN}_append_raspberrypi = " \
    libcec-rpi \
    vdr-plugin-rpihddevice \
    vdr-plugin-rpihddevice-locale-de-de \
    vdr-plugin-cecremote \
"

RRECOMMENDS_${PN}_append_raspberrypi2 = " \
    libcec-rpi \
    vdr-plugin-rpihddevice \
    vdr-plugin-rpihddevice-locale-de-de \
    vdr-plugin-cecremote \
"

RRECOMMENDS_${PN}_append_raspberrypi3 = " \
    libcec-rpi \
    vdr-plugin-rpihddevice \
    vdr-plugin-rpihddevice-locale-de-de \
    vdr-plugin-cecremote \
"

## Wetekplay specially
RRECOMMENDS_${PN}_append_wetekplay = " \
    wetek-dvb-modules \
    amlsetfb \
"
	
export IMAGE_BASENAME="mld-vdr-image"

