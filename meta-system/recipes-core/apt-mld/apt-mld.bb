SUMMARY = "Installieren der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "apt-mld"


LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


SRC_URI = " file://etc/apt/apt.conf.d/10periodic \
            file://etc/apt/apt.conf.d/15update \
            file://etc/apt/apt.conf.d/15upgrade \
            file://etc/apt/apt.conf.d/20archive \
            file://etc/apt/apt.conf.d/90auth \
            file://etc/apt/sources.list.d/devel.list.save \
            file://etc/apt/sources.list \
            file://etc/dpkg.d/apt.update \
            file://etc/init.d/apt \
            file://etc/setup/apt.sh \
            file://etc/setup/apt.xml \
            file://usr/bin/apt-message \
            file://var/www/tpl/packages/action.sh \
            file://var/www/tpl/packages/frame.sh \
            file://var/www/tpl/packages/index.sh \
            file://var/www/tpl/packages/system.sh \
            file://var/www/tpl/packages/upgrade.sh \
            file://var/www/tpl/packages/vdr.sh \
"

SRC_URI_append_arm = " file://alignment.sh"

do_install () {
	#
	# Directory erstellen
	#
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/apt
	install -d ${D}${sysconfdir}/apt/apt.conf.d
    install -d ${D}${sysconfdir}/apt/sources.list.d
    install -d ${D}${sysconfdir}/dpkg.d
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/setup
    install -d ${D}${bindir}
    install -d ${D}${localstatedir}
    install -d ${D}${localstatedir}/www
    install -d ${D}${localstatedir}/www/tpl
    install -d ${D}${localstatedir}/www/tpl/packages
    
	
	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/etc/apt/apt.conf.d/10periodic						${D}${sysconfdir}/apt/apt.conf.d/
    install -m 0755    ${WORKDIR}/etc/apt/apt.conf.d/15update						${D}${sysconfdir}/apt/apt.conf.d/
    install -m 0755    ${WORKDIR}/etc/apt/apt.conf.d/15upgrade                      ${D}${sysconfdir}/apt/apt.conf.d/
    install -m 0755    ${WORKDIR}/etc/apt/apt.conf.d/20archive                      ${D}${sysconfdir}/apt/apt.conf.d/
    install -m 0755    ${WORKDIR}/etc/apt/apt.conf.d/90auth                         ${D}${sysconfdir}/apt/apt.conf.d/

    install -m 0755    ${WORKDIR}/etc/apt/sources.list.d/devel.list.save            ${D}${sysconfdir}/apt/sources.list.d

    install -m 0755    ${WORKDIR}/etc/apt/sources.list                              ${D}${sysconfdir}/apt
    
    install -m 0755    ${WORKDIR}/etc/dpkg.d/apt.update                             ${D}${sysconfdir}/dpkg.d
    
    install -m 0755    ${WORKDIR}/etc/init.d/apt                                    ${D}${sysconfdir}/init.d

    install -m 0755    ${WORKDIR}/etc/setup/apt.sh                                  ${D}${sysconfdir}/setup
    install -m 0755    ${WORKDIR}/etc/setup/apt.xml                                 ${D}${sysconfdir}/setup

    install -m 0755    ${WORKDIR}/usr/bin/apt-message                               ${D}${bindir}

    install -m 0755    ${WORKDIR}/var/www/tpl/packages/action.sh                    ${D}${localstatedir}/www/tpl/packages    
    install -m 0755    ${WORKDIR}/var/www/tpl/packages/frame.sh                     ${D}${localstatedir}/www/tpl/packages
    install -m 0755    ${WORKDIR}/var/www/tpl/packages/index.sh                     ${D}${localstatedir}/www/tpl/packages 
    install -m 0755    ${WORKDIR}/var/www/tpl/packages/system.sh                    ${D}${localstatedir}/www/tpl/packages
    install -m 0755    ${WORKDIR}/var/www/tpl/packages/upgrade.sh                   ${D}${localstatedir}/www/tpl/packages 
    install -m 0755    ${WORKDIR}/var/www/tpl/packages/vdr.sh                       ${D}${localstatedir}/www/tpl/packages

}





