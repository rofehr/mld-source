SUMMARY = "MLD patch for apt package"
HOMEPAGE = "http://www.minidvblinux.de"


FILES_${PN}= "${sbindir}/extlinux"
FILES_${PN}+= "${base_prefix}/syslinux/isolinux.bin"
