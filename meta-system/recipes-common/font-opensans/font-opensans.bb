SUMMARY = "Open Sans is a humanist sans serif typeface"
LICENSE = "Apache-2.0"
AUTHOR = "Steve Matteson"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d273d63619c9aeaf15cdaf76422c4f87"

#SRC_URI = " http://www.google.com/fonts/download?kit=3hvsV99qyKCBS55e5pvb3ltkqrIMaAZWyLYEoB48lSQ;downloadfilename=open-sans-fonts.zip"
SRC_URI = " http://dl.1001fonts.com/open-sans.zip"

PR="r0"

#SRC_URI[md5sum] = "1ee77536f01277a6826cc7bb87433160"
#SRC_URI[sha256sum] = "b1e0925c81122343c10b4bd4f7406120394eee5203748a6ce9693e68780d090a"

#SRC_URI[md5sum] = "bc58a717471519a7f4a5f40a74285477"
#SRC_URI[sha256sum] = "ba3df6c0e20f2aea2a1f233c443402605d52d8b46a76fe0debfa2bf25c3fe049"

SRC_URI[md5sum] = "e628ed23fb53515a0d26644dd15c96be"
SRC_URI[sha256sum] = "e5ccdd548737eb7a9efffdbabeb8927bb448d02608a73a19833c6918dcb50fe9"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/usr/share/fonts
    find ./ -name '*.tt[cf]' -exec install -m 0644 {} ${D}/usr/share/fonts/ \;
}

FILES_${PN} += "${datadir}/fonts/*.ttf"
