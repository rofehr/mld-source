FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.13.15"

SRCREV = "2fb9a76925115250ed00e6dba09f3eeee63ecf71"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.13.y \
"
require linux-raspberrypi.inc
