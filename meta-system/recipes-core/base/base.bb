SUMMARY = "Init der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "base"


LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

 

SRC_URI = " file://etc/adjtime \
            file://etc/dialogrc \
            file://etc/fstab \
            file://etc/group \
            file://etc/gshadow \
            file://etc/host.conf \
            file://etc/hostname \
            file://etc/hosts \
            file://etc/hosts.allow \
            file://etc/hosts.deny \
            file://etc/inittab \
            file://etc/inputrc \
            file://etc/issue \
            file://etc/lsb-release \
            file://etc/networks \
            file://etc/passwd \
            file://etc/profile \
            file://etc/protocols \
            file://etc/rc.config \
            file://etc/resolv.conf \
            file://etc/services \
"

SRC_URI_append_arm = " file://alignment.sh"


do_install () {
	#
	# Directory erstellen
	#
	install -d ${D}${sysconfdir}

	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/etc/adjtime				    ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/dialogrc                  ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/fstab                     ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/group                     ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/gshadow                   ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/host.conf                 ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/hostname                  ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/hosts                     ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/hosts.allow               ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/hosts.deny                ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/inittab                   ${D}${sysconfdir}/inittab
    install -m 0755    ${WORKDIR}/etc/inputrc                   ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/issue                     ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/lsb-release               ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/networks                  ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/passwd                    ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/profile                   ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/protocols                 ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/rc.config                 ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/resolv.conf               ${D}${sysconfdir}
    install -m 0755    ${WORKDIR}/etc/services                  ${D}${sysconfdir}
}
