SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i)"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require linux.inc

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/linux-${PV}"
	
SRC_URI[md5sum] = "5de15914005314d964f37cff85a59048"
SRC_URI[sha256sum] = "6fb9f67002f986ce3905b35466e35c394e0fa3eb7c32df508514717955cb4de7"

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.14.1.tar.xz \
        file://defconfig \
        "
