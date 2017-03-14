FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.10.1"

SRCREV = "ec8d93b9b0f5e82f814622c47aa9639a7ff2466c"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.10.y \
"
require linux-raspberrypi.inc
