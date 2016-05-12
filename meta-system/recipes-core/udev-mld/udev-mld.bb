SUMMARY = "Init der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "udev-mld"
PACKAGE_ARCH = "all"

PR = "r2"

LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

 

SRC_URI = " file://etc/dpkg.d/udev \
            file://etc/init.d/udev \
            file://etc/udev/rules.d/11-usb-stick-auto-mount.rules \
            file://etc/udev/rules.d/20_custom.rules \
            file://etc/udev/rules.d/70-remote-device.rules \
            file://etc/udev/udev.conf \
            file://lib/udev/hotplug.functions \
            file://lib/udev/net.agent \
            file://lib/udev/rule_generator.functions \
            file://lib/udev/write_net_rules \
"

SRC_URI_append_arm = " file://alignment.sh"


do_install () {
	#
	# Directory erstellen
	#
    install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/dpkg.d
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/udev
    install -d ${D}${sysconfdir}/udev/rules.d
    
	
	#
	# Dateien installieren
	#
	
	install -m 0755    ${WORKDIR}/etc/dpkg.d/udev                                   ${D}${sysconfdir}/dpkg.d
    install -m 0755    ${WORKDIR}/etc/init.d/udev							        ${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/udev/rules.d/11-usb-stick-auto-mount.rules    ${D}${sysconfdir}/udev/rules.d
    install -m 0755    ${WORKDIR}/etc/udev/rules.d/20_custom.rules                  ${D}${sysconfdir}/udev/rules.d
    install -m 0755    ${WORKDIR}/etc/udev/rules.d/70-remote-device.rules           ${D}${sysconfdir}/udev/rules.d    
    install -m 0755    ${WORKDIR}/etc/udev/udev.conf                                ${D}${sysconfdir}/udev    

    install -d ${D}${base_libdir}/udev/

    install -m 0755    ${WORKDIR}/lib/udev/*.*                                      ${D}${base_libdir}/udev/
    

}

FILES_${PN} += " \
        ${base_libdir}/udev/hotplug.functions \
        ${base_libdir}/udev/net.agent \
        ${base_libdir}/udev/rule_generator.functions \
        ${base_libdir}/udev/write_net_rules \
"
