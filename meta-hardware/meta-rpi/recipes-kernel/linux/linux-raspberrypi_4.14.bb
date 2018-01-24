FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"


LINUX_VERSION ?= "4.14.1"

SRCREV = "e5a30c75d430a90b9e630049bee13aaea6f2c54e"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.14.y \
"

require linux-raspberrypi.inc
