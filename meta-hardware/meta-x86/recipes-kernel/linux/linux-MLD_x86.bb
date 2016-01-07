SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"
PACKAGE_ARCH = "${MACHINE_ARCH}"
KCONFIG_MODE = "--allnoconfig"
PR ?= "r0"
SRC = "20151106"


LINUX_VERSION_EXTENSION_append = "-MLD"
inherit kernel 

LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"


# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_kernel-base = "kernel-base"
PKG_kernel-image = "kernel-image"
RPROVIDES_kernel-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"

# Kernel 3.19.8
#LINUX_VERSION ?= "3.19.8"
#SRCREV = "fcf4fe0e3e820408890ae137a684e56010c55f99"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-3.19.y"

# Kernel 3.16.6
#LINUX_VERSION ?= "3.16.6"
#SRCREV = "6c11524352981b8276e8ded1c7b962a019f7967d"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-3.16.y"

# Kernel 3.16.1
#LINUX_VERSION ?= "3.16.1"
#SRCREV = "9a35988df62b6ce3b69d640da44a3ead96f63182"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-3.16.y"


# Kernel 3.16.7
#LINUX_VERSION ?= "3.16.7"
#SRCREV = "d0335e4feea0d3f7a8af3116c5dc166239da7521"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-3.16.y"

# Kernel 3.17.1
#LINUX_VERSION ?= "3.17.1"
#SRCREV = "9db8a8bb98cf75b0a51d66f560c87028e93a8ed8"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-3.17.y"
	
# Kernel 3.19.1
#LINUX_VERSION ?= "3.19.1"
#SRCREV = "5392bc6bce5ff16ca78d7d3780bde272f9119bb8"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-3.19.y"

# Kernel 4.3.3
LINUX_VERSION ?= "4.3.3"
SRCREV = "09f6b0600c331c69cdc8ba5d9152fe171745d8fd"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-4.3.y"		 
		 	 

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
