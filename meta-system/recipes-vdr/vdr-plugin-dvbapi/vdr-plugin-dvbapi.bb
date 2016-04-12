SUMMARY = "DVB Frontend Status Monitor plugin for VDR"
AUTHOR = "Rolf Ahrenberg"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://COPYING;md5"

PR = "r3"

#SRC_URI = "https://github.com/manio/vdr-plugin-dvbapi/archive/master.tar.gz"
#SRC_URI[md5sum] = "bf0daca4d8227be07733d4f642ee976b"
#SRC_URI[sha256sum] = "55b8967286cb6f395710bb3b96f9eb2da081b19ed180734aa86c465e0ce4785f"
#S = "${WORKDIR}/dvbapi"

SRC_URI = "git://github.com/manio/vdr-plugin-dvbapi.git"
SRCREV = "ce8a124314bd0410ecbcc288810d6dc71cc6c845"
S = "${WORKDIR}/git"


SRC_URI_append = " \
   file://10_dvbapi_libdvbcsa.patch \   
   file://11_dvbapi_mld.patch \   
"

DEPENDS = "vdr"

CXXFLAGS_append = " -fPIC -D_FILE_OFFSET_BITS=64 -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE"

EXTRA_OEMAKE = ' \
	STRIP=/bin/true \
'

do_install() {
	oe_runmake DESTDIR=${D} install
}

FILES_${PN} += " \
	${libdir}/vdr/* \
"

FILES_${PN}-dbg += " \
	${libdir}/vdr/.debug/* \
"
FILES_${PN}-locale = "${datadir}/locale"



