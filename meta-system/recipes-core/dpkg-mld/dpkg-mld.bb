SUMMARY = "Init der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "dpkg-mld"


LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

 

SRC_URI = " file://etc/dpkg/dpkg.cfg \
            file://etc/dpkg/dpkg.invoke \
            file://etc/dpkg.d/dpkg.log \
            file://usr/bin/tar \
            file://usr/sbin/tar \
"

SRC_URI_append_arm = " file://alignment.sh"

do_install () {
	#
	# Directory erstellen
	#

	install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/dpkg
    install -d ${D}${sysconfdir}/dpkg.d
    
    #/usr/bin
    install -d ${D}${bindir}

    #/usr/sbin
	install -d ${D}${sbindir}
	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/etc/dpkg/dpkg.cfg			    ${D}${sysconfdir}/dpkg
    install -m 0755    ${WORKDIR}/etc/dpkg/dpkg.invoke          ${D}${sysconfdir}/dpkg

    install -m 0755    ${WORKDIR}/etc/dpkg.d/dpkg.log           ${D}${sysconfdir}/dpkg.d

    install -m 0755    ${WORKDIR}/usr/bin/tar                   ${D}${bindir}

    install -m 0755    ${WORKDIR}/usr/sbin/tar                  ${D}${sbindir}

}
