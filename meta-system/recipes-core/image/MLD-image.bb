#SUMMARY = "A MLD base image ."

# Wechsel auf core-image-minimal-initramfs Image, da nur minimales Bootsystem gebraucht wird.
#include recipes-core/images/core-image-base.bb
#include recipes-core/images/core-image-minimal-initramfs.bb


#LICENSE = "MIT"
#PACKAGE_ARCH = "${MACHINE_ARCH}"
#LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
#                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

# in dem MLD-netinstall image wurden ursprünglich folgende Pakete benötigt. Vernachlässigt werden könnten für den 1.Start die Plugins: locales backup live webserver
# netinstall        = locales backup install install-net live webserver ssh
# base              = xfs extlinux busybox initramfs

#IMAGE_FEATURES += "splash"

#IMAGE_INSTALL += " \
#        ssh \
#"

#export IMAGE_BASENAME="MLD-Base-image"

SUMMARY = "A MLD base image ."
DESCRIPTION = "An image containing the BASE system for MLD ${DISTRO_VERSION} "
HOMEPAGE = "http://www.minidvblinux.de"

include recipes-core/images/core-image-minimal.bb


LICENSE = "MIT"

# Roor Passwort setzen :)
inherit extrausers
EXTRA_USERS_PARAMS = "usermod -P mld600 root;"


IMAGE_INSTALL += " \
	packagegroup-mld-netinstall \
" 

#syslinux_iso_populate_append() {
#	install -m 0444 ${STAGING_DATADIR}/syslinux/libcom32.c32 ${ISODIR}${ISOLINUXDIR}
#	install -m 0444 ${STAGING_DATADIR}/syslinux/mboot.c32 ${ISODIR}${ISOLINUXDIR}
#}



export IMAGE_BASENAME="MLD-Base-image"
