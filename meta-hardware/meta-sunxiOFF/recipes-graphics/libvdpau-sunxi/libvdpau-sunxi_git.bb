DESCRIPTION = "libvdpau-sunxi"

LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=edf7fb6071cae7ec80d537a05ee17198"

inherit autotools pkgconfig

PV = "r4"
SRCREV_pn-${PN} = "743bdfbc81832dc7317b5a79b4d90dcfe1025ff8"

SRC_URI = "git://github.com/linux-sunxi/libvdpau-sunxi.git"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}/${libdir}
    install -m 755 ${S}/libvdpau-sunxi ${D}/${libdir}
}




