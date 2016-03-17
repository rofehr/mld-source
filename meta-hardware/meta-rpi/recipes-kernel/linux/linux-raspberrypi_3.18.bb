FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "3.18.16"

SRCREV = "1bb18c8f721ef674a447f3622273f2e2de7a205c"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.18.y \
           file://0001-dts-add-overlay-for-pitft22.patch \
          "
require linux-raspberrypi.inc

# Create missing out of tree 'overlays' directory prior to install step
do_compile_prepend() {
  mkdir -p ${B}/arch/arm/boot/dts/overlays
}

# Really supported starting from linux-raspberrypi 3.18.y only
KERNEL_DEVICETREE ?= " \
    bcm2708-rpi-b.dtb \
    bcm2708-rpi-b-plus.dtb \
    bcm2709-rpi-2-b.dtb \
    bcm2710-rpi-3-b.dtb \
    \
    overlays/hifiberry-amp-overlay.dtb \
    overlays/hifiberry-dac-overlay.dtb \
    overlays/hifiberry-dacplus-overlay.dtb \
    overlays/hifiberry-digi-overlay.dtb \
    overlays/i2c-rtc-overlay.dtb \
    overlays/iqaudio-dac-overlay.dtb \
    overlays/iqaudio-dacplus-overlay.dtb \
    overlays/lirc-rpi-overlay.dtb \
    overlays/pitft22-overlay.dtb \
    overlays/pps-gpio-overlay.dtb \
    overlays/w1-gpio-overlay.dtb \
    overlays/w1-gpio-pullup-overlay.dtb \
"
