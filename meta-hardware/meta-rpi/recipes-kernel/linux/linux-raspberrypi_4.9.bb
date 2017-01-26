FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.4"

SRCREV = "fd03479116aebe4a9faf64e108b3ab7eeb8cb71a"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
"
require linux-raspberrypi.inc
