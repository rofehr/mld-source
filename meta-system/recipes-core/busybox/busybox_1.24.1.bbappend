
# look for files in the layer first
# FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/patch:"

SRC_URI += "file://10_busybox_1.23.0-ssd.patch \
            file://11_busybox_1.23.0-wget_post.patch \
            file://MLD.cfg \
"
            
            
