SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"
KCONFIG_MODE = "--allnoconfig"
PR = "r0"
SRC = "20151106"

LINUX_VERSION ?= "3.16.1"

inherit kernel 
require linux-dtb.inc


SRC_URI[md5sum] = "e7a985a243b7941b6bc6240fcbc797fc"
SRC_URI[sha256sum] = "be37dda8ea090525661d64e5c7fc8580f313b7f9ba8592e32120f1332bc57d71"

LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"


# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_kernel-base = "kernel-base"
PKG_kernel-image = "kernel-image"
RPROVIDES_kernel-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"

SRC_URI += "https://www.kernel.org/pub/linux/kernel/v3.x/linux-${PV}.tar.xz \
        file://defconfig \
        file://COPYING.GPL \
        "

S = "${WORKDIR}/linux-MLD"
B = "${WORKDIR}/build"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"


do_rm_work() {
}
