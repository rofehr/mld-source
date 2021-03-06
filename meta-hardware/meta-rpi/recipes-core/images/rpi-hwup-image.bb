# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

# Include modules in rootfs
IMAGE_INSTALL += " \
	kernel-modules \
	"
	
COMPATIBLE_HOST = "(raspberrypi |raspberrypi2 |raspberrypi3)"	

do_image_prepend() {
    bb.warn("The image 'rpi-hwup-image' is deprecated, please use 'core-image-minimal' instead")
}
