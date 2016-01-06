SUMMARY = "Installieren der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "install"


LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


SRC_URI = " file://etc/setup/install.sh \
            file://etc/setup/install.xml.bpi \
            file://etc/setup/install.xml.rpi \
            file://etc/setup/install.xml.wtk \
            file://etc/setup/install.xml.x86 \
            file://usr/bin/install \
            file://usr/sbin/install.sh \
            file://var/www/tpl/index/20_install.sh \
            file://var/www/tpl/navi/system/install.sh \
            file://var/www/tpl/setup/0Install/10_headline.sh \
            file://var/www/tpl/setup/0Install/27_reboot.sh \
            file://var/www/tpl/setup/0Install/95_reboot.sh \
            file://var/www/tpl/setup/0Install/Manual/80_warning.sh \
"

SRC_URI_append_arm = " file://alignment.sh"


do_install () {
	#
	# Directory erstellen
	#
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/setup
	install -d ${D}${bindir}
	install -d ${D}${sbindir}
	install -d ${D}${localstatedir}/cache
	install -d ${D}${localstatedir}/www
	install -d ${D}${localstatedir}/www/tpl
	install -d ${D}${localstatedir}/www/tpl/index
	install -d ${D}${localstatedir}/www/tpl/navi
	install -d ${D}${localstatedir}/www/tpl/navi/system
	install -d ${D}${localstatedir}/www/tpl/setup.d/0Install
	install -d ${D}${localstatedir}/www/tpl/setup.d/0Install/Manual

	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/etc/setup/install.sh							${D}${sysconfdir}/setup
    install -m 0755    ${WORKDIR}/etc/setup/install.xml.x86						${D}${sysconfdir}/setup/install.xml
    #install -m 0755    ${WORKDIR}/etc/setup/install.xml.bpi					${D}${sysconfdir}/setup
    #install -m 0755    ${WORKDIR}/etc/setup/install.xml.rpi					${D}${sysconfdir}/setup
    #install -m 0755    ${WORKDIR}/etc/setup/install.xml.wtk					${D}${sysconfdir}/setup

    
    install -m 0755    ${WORKDIR}/usr/bin/install								${D}${bindir}
    
    install -m 0755    ${WORKDIR}/usr/sbin/install.sh							${D}${sbindir}
    
    install -m 0755    ${WORKDIR}/var/www/tpl/index/20_install.sh							${D}${localstatedir}/www/tpl/index
    install -m 0755    ${WORKDIR}/var/www/tpl/navi/system/install.sh						${D}${localstatedir}/www/tpl/navi/system
    install -m 0755    ${WORKDIR}/var/www/tpl/setup/0Install/10_headline.sh					${D}${localstatedir}/www/tpl/setup.d/0Install
    install -m 0755    ${WORKDIR}/var/www/tpl/setup/0Install/27_reboot.sh					${D}${localstatedir}/www/tpl/setup.d/0Install
    install -m 0755    ${WORKDIR}/var/www/tpl/setup/0Install/95_reboot.sh					${D}${localstatedir}/www/tpl/setup.d/0Install
    install -m 0755    ${WORKDIR}/var/www/tpl/setup/0Install/Manual/80_warning.sh			${D}${localstatedir}/www/tpl/setup.d/0Install/Manual
	
}





