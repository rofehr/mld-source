# Das netinstall package 
# Beschreibt den Inhalt MLD Packages 

DESCRIPTION = "MLD Packages"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup 

PACKAGES = " \
 packagegroup-mld-base \
 packagegroup-mld-cmdline \
 packagegroup-mld-kernel-modules \
 packagegroup-mld-install \
 packagegroup-mld-tools \
"

#RDEPENDS_packagegroup-mld-base = " \
#	psplash \
#	busybox \
#	init \
#   gettext \
#  gettext-runtime \
#"

RDEPENDS_packagegroup-mld-base = " \
    busybox \
    udev \
"

RDEPENDS_packagegroup-mld-cmdline = " \
	initscripts \
	sysvinit \
    acl \
    attr \
    nfs-utils \
"

RDEPENDS_packagegroup-mld-kernel-modules = " \
	kernel-modules \
"

RDEPENDS_packagegroup-mld-install = " \
	init \
	install \
	gettext \
	gettext-runtime \
	ssh \
"

RDEPENDS_packagegroup-mld-tools = " \
	mc \
	nano \
"


