SUMMARY = "Init der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "init-mld"

PR = "r2"

LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

 

SRC_URI = " file://init \
            file://etc/init.d/dma \
            file://etc/init.d/example \
            file://etc/init.d/fsck \
            file://etc/init.d/initramfs \
            file://etc/init.d/localhost \
            file://etc/init.d/mount \
            file://etc/init.d/rc.functions \
            file://etc/init.d/rc.init \
            file://etc/init.d/rc.poweroff \
            file://etc/init.d/rc.sysinit \
            file://etc/setup/init.sh \
            file://etc/setup/init.xml \
            file://sbin/restart \
            file://sbin/shutdown \
            file://sbin/start \
            file://sbin/stop \
"

SRC_URI_append_arm = " file://alignment.sh"


do_install () {
	#
	# Directory erstellen
	#
    
    install -d ${D}${base_prefix}
	
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/setup.d
	install -d ${D}${sbindir}
    install -d ${D}/var/
    install -d ${D}/var/log
    install -d ${D}/var/spool
	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/init                                      ${D}
	
	#install -m 0755    ${WORKDIR}/init                                      ${IMAGE_ROOTFS}
	
    install -m 0755    ${WORKDIR}/etc/init.d/dma							${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/example						${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/fsck							${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/initramfs                      ${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/localhost						${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/mount							${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/rc.functions					${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/rc.init						${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/rc.poweroff					${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/init.d/rc.sysinit						${D}${sysconfdir}/init.d

    install -m 0755    ${WORKDIR}/etc/setup/init.sh							${D}${sysconfdir}/setup.d
    install -m 0755    ${WORKDIR}/etc/setup/init.xml						${D}${sysconfdir}/setup.d
    
    install -m 0755    ${WORKDIR}/sbin/restart								${D}${sbindir}
    install -m 0755    ${WORKDIR}/sbin/shutdown								${D}${sbindir}
    install -m 0755    ${WORKDIR}/sbin/start								${D}${sbindir}
    install -m 0755    ${WORKDIR}/sbin/stop									${D}${sbindir}
    
    install -m 0755    ${WORKDIR}/init                                      ${D}${base_prefix}
}

FILES_${PN} += " \
        /init \
        /etc/init.d/dma \
        /etc/init.d/example \
        /etc/init.d/fsck \
        /etc/init.d/initramfs \
        /etc/init.d/localhost \
        /etc/init.d/mount \
        /etc/init.d/rc.functions \
        /etc/init.d/rc.init \
        /etc/init.d/rc.poweroff \
        /etc/init.d/rc.sysinit \
        /etc/setup/init.sh \
        /etc/setup/init.xml \
        /sbin/restart \
        /sbin/shutdown \
        /sbin/start \
        /sbin/stop \
        "

