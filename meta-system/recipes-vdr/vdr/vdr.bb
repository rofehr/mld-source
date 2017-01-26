DESCRIPTION = "Video Disk Recorder (VDR) is a digital sat-receiver program using Linux and DVB technologies. It allows to record broadcasts, as well as output the stream to TV."
SUMMARY = "Video Disk Recorder"
HOMEPAGE = "http://www.tvdr.de"
SECTION = "console/multimedia"
LICENSE = "GPLv2"
AUTHOR = "Klaus Schmidinger"

PR="r0"

# the current version
PV = "2.2.0"
SRC_URI = "ftp://ftp.tvdr.de/vdr/${P}.tar.bz2"

SRC_URI[md5sum] = "8853f64c0fc3d41ffd3b4bfc6f0a14b7"
SRC_URI[sha256sum] = "7c259e1ed1f39d93d23df1d5d0f85dd2a1fa9ec1dadff79e5833e2ff3ebf6c4e"

#PV = "2.3.1"
#SRC_URI = "ftp://ftp.tvdr.de/vdr/Developer/${P}.tar.bz2"

#SRC_URI[md5sum] = "391c2ed60e2f7d24563fe3ed5854bc4f"
#SRC_URI[sha256sum] = "456d3f0ceb699b92ebeaf9ff4c0c68979724d10b01a89d5250133e6f4c262fa7"


LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI_append = " \
    file://etc/vdr/channels.conf \
    file://etc/vdr/sources.conf \
	file://remotetimers.patch \
	file://vdr-1.7.21-pluginmissing.patch \
	file://vdr-1.7.29-menuselection.patch \
	file://MainMenuHooks-v1_0_3.patch \
	file://vdr-2.2.0_horizontal_menu.patch \
    file://0_jpeg.patch \	
"

DEPENDS = " \
	fontconfig \
	freetype \
	ttf-bitstream-vera \
	gettext \
	jpeg \
	libcap \
	virtual/libintl \
	ncurses \
"

RDEPENDS_${PN} += "perl"

PLUGINDIR = "${libdir}/vdr"

CXXFLAGS += "-fPIC"
CFLAGS += "-fPIC"

do_configure_append() {
    cat > Make.config <<-EOF
	## The C compiler options:
	CFLAGS   = ${CFLAGS} -Wall
	CXXFLAGS = ${CFLAGS} -Wall
	### The directory environment:
	PREFIX   = ${prefix}
	BINDIR   = ${bindir}
	INCDIR   = ${includedir}
	LIBDIR   = ${libdir}/vdr
	LOCDIR   = ${datadir}/locale
	MANDIR   = ${mandir}
	PCDIR    = ${libdir}/pkgconfig
	RESDIR   = ${datadir}/vdr

	VIDEODIR = /srv/vdr/video
	CONFDIR  = ${sysconfdir}/vdr
	ARGSDIR  = ${sysconfdir}/vdr/conf.d
	CACHEDIR = /var/cache/vdr
	EOF
}

do_compile () {
	oe_runmake 'DESTDIR=${D}' vdr
}

do_install () {
    install -d ${D}${base_prefix}
	
	oe_runmake 'DESTDIR=${D}' install-bin install-i18n install-includes install-pc
}

FILES_${PN} = " \
       ${bindir}/* \
       ${localstatedir}/cache/vdr \
       ${datadir}/vdr \
       ${base_prefix}/etc/vdr/channels.conf \
       ${base_prefix}/etc/vdr/sources.conf \
       ${base_prefix}/srv/vdr/video \
        "


FILES_${PN}-dbg += "${PLUGINDIR}/.debug/*"

FILES_${PN}-locale = "${datadir}/locale"

