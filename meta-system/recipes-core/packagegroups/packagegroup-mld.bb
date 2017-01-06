# Das netinstall package 
# Beschreibt den Inhalt MLD Packages 

DESCRIPTION = "MLD Packages"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup 




RDEPENDS_packagegroup-mld-x86 = " \
    extlinux \
    syslinux-extlinux \
"




RDEPENDS_packagegroup-mld-kernel-modules = " \
	kernel-modules \
"

RDEPENDS_packagegroup-mld-install = " \
	init \
	install \
"

RDEPENDS_packagegroup-mld-tools = " \
	mc \
	nano \
"

RDEPENDS_packagegroup-mld-test-new-recipes = " \
	vtunerc \
	satip-client \
	packagegroup-core-eclipse-debug \
	packagegroup-core-ssh-openssh \
"


