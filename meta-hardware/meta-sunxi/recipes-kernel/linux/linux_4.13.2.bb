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
	
SRC_URI[md5sum] = "110c836a123bca14c2999471ae0f1b8a"
SRC_URI[sha256sum] = "064adc177a384a7aee6b18ef5d47c1cea3a43fae1aaa6aa95fdc97eb137ffcd1"

#https://git.kernel.org/torvalds/t/linux-4.13.2.tar.gz

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.13.2.tar.xz \
        file://defconfig \
        "
