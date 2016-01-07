SUMMARY = "Extlinux boot loader"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "extlinux"


LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

 

SRC_URI = " file://usr/lib/extlinux/extlinux.conf \
			file://usr/lib/extlinux/vesamenu.c32 \
"

SRC_URI_append_arm = " file://alignment.sh"


do_install () {
	#
	# Directory erstellen
	#
	install -d ${D}/usr
	install -d ${D}/usr/lib
	install -d ${D}/usr/lib/extlinux
	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/usr/lib/extlinux/extlinux.conf				${D}/usr/lib/extlinux/extlinux.conf
    install -m 0755    ${WORKDIR}/usr/lib/extlinux/vesamenu.c32					${D}/usr/lib/extlinux/vesamenu.c32	
}
