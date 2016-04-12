SUMMARY = "VDR HD output device for Amlogic SoC."
AUTHOR = "Thomas Reufer <thomas@reufer.ch>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

PR = "r9"

SRCREV = "b0fe61c70c862afcb5defb2cede3dac32587f475"
SRC_URI = "git://projects.vdr-developer.org/vdr-plugin-amlhddevice.git \
           file://codec.h \
           file://10_include.patch \
"


S = "${WORKDIR}/git"

DEPENDS = " libamcodec \
	        vdr \
"

CXXFLAGS_append = " -fPIC -D_FILE_OFFSET_BITS=64 -D_LARGEFILE_SOURCE -D_LARGEFILE64_SOURCE"

FILES_${PN} += " \
    ${libdir}/vdr/* \
"

FILES_${PN}-dbg += " \
    ${libdir}/vdr/.debug/* \
"
FILES_${PN}-locale = "${datadir}/locale"


do_copy() {
  cp ${WORKDIR}/codec.h ${S}
}    

do_compile_prepend() {
    mkdir -p ${S}/po
}

do_install() {
    oe_runmake DESTDIR=${D} install
}

addtask copy after do_patch before do_compile



