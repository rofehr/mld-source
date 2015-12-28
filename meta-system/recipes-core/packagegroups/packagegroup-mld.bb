# Das netinstall package 
# Beschreibt den Inhalt des 'netinstall' packages 

DESCRIPTION = "netinstall package"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = " \
 packagegroup-mld-base \
 packagegroup-mld-netinstall \
"

RDEPENDS_packagegroup-mld-base = " \
	psplash \
"

RDEPENDS_packagegroup-mld-netinstall = " \
    packagegroup-mld-base \
	busybox \
	ssh \
	install \
"


