DESCRIPTION = "MLD satip-client Package"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup 

PACKAGES = " \
 packagegroup-mld-output-device-rpi \
"

RDEPENDS_packagegroup-mld-output-device-rpi = " \
    vdr-plugin-rpihddevice \
"