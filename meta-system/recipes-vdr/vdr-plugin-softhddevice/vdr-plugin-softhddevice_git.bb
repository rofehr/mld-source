SUMMARY = "VDR softhddevice plugin"
LICENSE = "GPLv2" 
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRCREV = "  6dfa88aecf1b5a4c5932ba278209d9f22676547f"
SRC_URI = "git://projects.vdr-developer.org/git/vdr-plugin-softhddevice.git;protocol=http"

PR="r1"

S = "${WORKDIR}/git"

DEPENDS = " \
	ffmpeg \
	vdr \
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
	${datadir}/vdr/plugins/softhddevice/* \
"

FILES_${PN}-dbg += " \
	${libdir}/vdr/.debug/* \
"
FILES_${PN}-locale = "${datadir}/locale"
