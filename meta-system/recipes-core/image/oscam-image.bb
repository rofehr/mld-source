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

## Stuff
IMAGE_INSTALL += " \    
    oscam \
    "   
  

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
    kmod \
    usbutils \
    apm \
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

export IMAGE_BASENAME="oscam-image"

COMPATIBLE_HOST = "(i.86|x86_64).*-linux"