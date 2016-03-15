DESCRIPTION = "Web-based AJAX terminal emulator"
HOMEPAGE = "http://code.google.com/p/shellinabox/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a193d25fdef283ddce530f6d67852fa5"

PR = "r0"

S = "${WORKDIR}/git"

SRC_URI = " git://github.com/shellinabox/shellinabox.git \
"
SRCREV = "1a8010f2c94a62e7398c4fa130dfe9e099dc55cd"

SRC_URI[md5sum] = "c20e4db00191eec900d1ef5a54f395aa"
SRC_URI[sha256sum] = "d0864af6082932a883d99fbb1ddf21b919bcfc7b1e3080ad3826022d0d992a22"

RDEPENDS_${PN} += "bash git "

inherit autotools

#do_configure () {
#    autoreconf -i --force 
#    ./configure
#    make
#}

#do_install() {
#}

