SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"
KCONFIG_MODE = "--allnoconfig"
PR = "r0"
SRC = "20151106"

LINUX_VERSION ?= "3.16.1"

LINUX_VERSION_EXTENSION_append = "-mld"
inherit kernel 

LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"


# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_kernel-base = "kernel-base"
PKG_kernel-image = "kernel-image"
RPROVIDES_kernel-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"

SRCREV = "d0335e4feea0d3f7a8af3116c5dc166239da7521"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-3.16.y"

SRC_URI += " file://defconfig \
        	 file://COPYING.GPL \
        "

PV = "${LINUX_VERSION}+git${SRCPV}"

S = "${WORKDIR}/git"


export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"



do_rm_work() {
}
