DESCRIPTION = "Unified Memory Provider userspace API source code needed for xf86-video-mali compilation"

#LICENSE = "GPLv2"
#LIC_FILES_CHKSUM = "file://LICENSE;"

LICENSE = "CLOSED"

#inherit autotools pkgconfig

PV = "r1"

#SRC_URI[md5sum] = "0bfece2f193ade325aa158dc2e88a888"
#SRC_URI[sha256sum] = "f8ca3d7cbe8ce79436cd878de9fd59407178c5228a8053359607bf6f141dc461"
#SRC_URI = "https://github.com/mzakharo/sunxi_cedar.git;protocol=https"

SRCREV = "e9cafdc737de766723d54e8dbc26274f335b2a84"

SRC_URI = "git://github.com/rofehr/sunxi_cedar.git;protocol=git"

S = "${WORKDIR}/git"

do_compile () {
    oe_runmake 'DESTDIR=${S}' all 
}

