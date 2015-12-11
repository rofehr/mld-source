SUMMARY = "A mld base image ."

include recipes-core/images/core-image-base.bb


LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
                    

IMAGE_FEATURES += "splash"

IMAGE_INSTALL += " \
	nano \
	kernel-modules \
	tiny-init \
	mc \
"



export IMAGE_BASENAME="mld-image"