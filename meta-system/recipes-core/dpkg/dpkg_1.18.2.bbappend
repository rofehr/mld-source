SUMMARY = "MLD patch for dpkg4 package"
HOMEPAGE = "http://www.minidvblinux.de"

EXTRA_OECONF = "\
        --disable-dselect \
        --disable-start-stop-daemon \
        --with-zlib \
        --with-bz2 \
        --without-liblzma \
        --without-selinux \
        "
        
