SUMMARY = "Installieren der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "network"

DEPENDS += "ethtool ifupdown"

LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


SRC_URI = " file://etc/nsswitch.conf \
			file://etc/init.d/network \
            file://etc/setup/network.sh \
            file://etc/setup/network.xml \
            file://usr/bin/update_network \
            file://usr/bin/update_server.sh \
            file://usr/share/udhcpc/default.script \           
"

SRC_URI_append_arm = " file://alignment.sh"

do_install () {
	#
	# Directory erstellen
	#
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/setup
	install -d ${D}${bindir}
	install -d ${D}${datadir}
	install -d ${D}${localstatedir}/run    
	
	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/etc/nsswitch.conf							${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/init.d/network						${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/setup/network.sh						${D}${sysconfdir}/setup
    install -m 0755    ${WORKDIR}/etc/setup/network.xml						${D}${sysconfdir}/setup

    install -m 0755    ${WORKDIR}/usr/bin/update_network					${D}${bindir}
    install -m 0755    ${WORKDIR}/usr/bin/update_server.sh					${D}${bindir}
    
    install -m 0755    ${WORKDIR}/usr/share/udhcpc/default.script           ${D}${datadir}
}





