SUMMARY = "urldecode"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "urldecode"
PACKAGE_ARCH = "all"

LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR= "r0"

SRC_URI = " file://urldecode.cc \
"

S = "${WORKDIR}"


do_compile() {
       ${CXX} ${WORKDIR}/urldecode.cc -o ${WORKDIR}/urldecode
}

do_install () {
    #
    # Directory erstellen
    #
    install -d ${D}${bindir}
    
    
    #
    # Dateien installieren
    #
    install -m 0755    ${S}/urldecode                       ${D}${bindir}
}