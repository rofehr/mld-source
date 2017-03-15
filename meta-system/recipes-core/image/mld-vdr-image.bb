include recipes-core/images/core-image-minimal.bb

SPLASH = "psplash"

base_sbindir_progs = "True"
base_bindir_progs = "True"

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
	"

	
## VDR Stuff	
IMAGE_INSTALL += "\
	vdr-font-symbols \
	vdr \
	vdr-locale-de-de \
	vdr-plugin-mlist \
	vdr-plugin-mlist-locale-de-de \
	vdr-plugin-femon \
	vdr-plugin-femon-locale-de-de \
	vdr-plugin-satip \
	vdr-plugin-satip-locale-de-de \
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
	vdr-plugin-skinenigmang-logos-xpm-hi \
	"
	
  	
IMAGE_INSTALL_append = "\
    glibc-utils \
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
	glibc-gconv-unicode \
    net-tools \
	"
	
IMAGE_INSTALL += " \
    keymaps \
    kbd \
    nano \
    poco \
    "
    
## MLD-Stuff
IMAGE_INSTALL += " \
    apt \
    apt-mld \
    base \
    dpkg \
    dpkg-mld \
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
    	


## raspberry specially
RRECOMMENDS_${PN}_append_rpi = " \
    libcec-rpi \
    vdr-plugin-rpihddevice \
    vdr-plugin-rpihddevice-locale-de-de \
    vdr-plugin-cecremote \
"

RRECOMMENDS_${PN}_append_rpi2 = " \
    libcec-rpi \
    vdr-plugin-rpihddevice \
    vdr-plugin-rpihddevice-locale-de-de \
    vdr-plugin-cecremote \
"

RRECOMMENDS_${PN}_append_rpi3 = " \
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

