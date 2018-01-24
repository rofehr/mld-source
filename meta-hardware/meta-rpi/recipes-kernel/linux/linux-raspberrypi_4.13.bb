FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.13.15"

SRCREV = "59341bd7414bf64cc5cddfa422fc81cdf0451fab"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.13.y \
"
require linux-raspberrypi.inc
