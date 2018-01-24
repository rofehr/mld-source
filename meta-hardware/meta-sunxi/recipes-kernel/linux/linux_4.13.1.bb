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
	
SRC_URI[md5sum] = "ba3728f0c0b6e5aa5823b1cab0556498"
SRC_URI[sha256sum] = "decee7a2de34aea921fce3e7934f520790fe43176de29bd9718a84419ca6e1ce"

#https://git.kernel.org/torvalds/t/linux-4.13.1.tar.gz

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-4.13.1.tar.xz \
        file://defconfig \
        "
