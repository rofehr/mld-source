SUMMARY = "VDR plex plugin"
LICENSE = "CLOSED" 

SRCREV = "135ed5d0ce1613f70f4b2ddcb9e8bca721ffca9e"
SRC_URI = "git://projects.vdr-developer.org/vdr-plugin-plex.git"

PR="r13"

S = "${WORKDIR}/git"

DEPENDS = " \
	vdr \
	vdr-plugin-skindesigner \
	libpoco \
"

RDEPENDS_${PN} += "bash git"

CXXFLAGS_append = " -fPIC -D_FILE_OFFSET_BITS=64 -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE"

EXTRA_OEMAKE = ' \
	SDKSTAGE="${STAGING_DIR_HOST}" \
'

do_install() {
	oe_runmake DESTDIR=${D} PREFIX=/usr install
}

FILES_${PN} = " \
	${libdir}/vdr/* \
"

FILES_${PN}-dbg += " \
	${libdir}/vdr/.debug/* \
"
FILES_${PN}-locale = "${datadir}/locale"

