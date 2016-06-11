SUMMARY = "Init der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "initramfs"

PR = "r1"

LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

 

SRC_URI = " file://etc/init.d/initramfs \
"

SRC_URI_append_arm = " file://alignment.sh"


do_install () {
	#
	# Directory erstellen
	#
	
	install -d ${D}${sysconfdir}/init.d
	
	#
	# Dateien installieren
	#
	
    install -m 0755    ${WORKDIR}/etc/init.d/initramfs						${D}${sysconfdir}/init.d
    
}
