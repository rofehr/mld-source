SUMMARY = "WebIf der MLD"
AUTHOTR = "Claus Muus <mld@clausmuus.de>"
HOMEPAGE ="http://www.minidvblinux.de"
SECTION = "base"
PN = "webserver"

DEPENDS =" gettext \
"

LICENSE = "CLOSED"
#LICENSE = "GPLv2"
#LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI_append_arm = " file://alignment.sh"

#SRC_URI = " file://usr/bin/urldecode \
#"


SRC_URI = " file://etc/applications/register.d/webserver \
			file://etc/init.d/webserver \
			file://etc/setup/webserver.sh \
			file://etc/setup/webserver.xml \
"

SRC_URI += " file://usr/bin/get_debug_log_collection.sh \
			 file://usr/bin/update_quick_start.sh \
			 file://usr/bin/update_web_setup.sh \
			 file://usr/bin/webserver.sh \
"

SRC_URI += " file://usr/share/doc/webserver/copyright \
			 file://usr/share/doc/webserver/history \
"			 

SRC_URI += " file://usr/share/locale/de_DE/LC_MESSAGES/webserver-update_quick_start.sh.mo \
			 file://usr/share/locale/de_DE/LC_MESSAGES/webserver-update_web_setup.sh.mo \
			 file://usr/share/locale/de_DE/LC_MESSAGES/webserver-webserver.mo \
			 file://usr/share/locale/de_DE/LC_MESSAGES/webserver-webserver.xml.mo \
			 file://usr/share/locale/de_DE/LC_MESSAGES/webserver-www.mo \
"

SRC_URI += " file://usr/share/locale/en_GB/LC_MESSAGES/webserver-update_quick_start.sh.mo \
			 file://usr/share/locale/en_GB/LC_MESSAGES/webserver-update_web_setup.sh.mo \
			 file://usr/share/locale/en_GB/LC_MESSAGES/webserver-webserver.mo \
			 file://usr/share/locale/en_GB/LC_MESSAGES/webserver-webserver.xml.mo \
			 file://usr/share/locale/en_GB/LC_MESSAGES/webserver-www.mo \
"

SRC_URI += " file://var/www/images/bg.jpg \
			 file://var/www/images/bg_headline.png \	
			 file://var/www/images/bg_navi.png \	
			 file://var/www/images/bg_navi_active.png \	
			 file://var/www/images/favicon.ico \	
			 file://var/www/images/icon_collapse.png \	
			 file://var/www/images/icon_community.png \	
			 file://var/www/images/icon_download.png \	
			 file://var/www/images/icon_expand.png \	
			 file://var/www/images/icon_feed.png \	
			 file://var/www/images/icon_home.png \	
			 file://var/www/images/icon_kind.png \	
			 file://var/www/images/icon_list.png \	
			 file://var/www/images/icon_rss.png \	
			 file://var/www/images/icon_sortorder.png \	
			 file://var/www/images/logo.png \	
"

SRC_URI += " file://var/www/tpl/about/10_about.sh \
			 file://var/www/tpl/about/20_packages.sh \
			 file://var/www/tpl/about/30_mld.sh \
			 file://var/www/tpl/about/40_packages.sh \
"

SRC_URI += " file://var/www/tpl/commands/system.sh \
			 file://var/www/tpl/commands/system/poweroff.sh \
			 file://var/www/tpl/commands/system/reboot.sh \
"

SRC_URI += " file://var/www/tpl/index/10_welcome.sh \
			 file://var/www/tpl/index/15_services.sh \
			 file://var/www/tpl/index/50_quickstart.sh \
			 file://var/www/tpl/index/90_errorhandling.sh \
"
SRC_URI += " file://var/www/tpl/logfiles/messages.sh \
			 file://var/www/tpl/logfiles/sysinit.sh \
			 file://var/www/tpl/logfiles/zz_errorhandling.sh \
"

SRC_URI += " file://var/www/tpl/navi/services/apps.sh \
			 file://var/www/tpl/navi/system/commands.sh \
			 file://var/www/tpl/navi/system/info.sh \
			 file://var/www/tpl/navi/system/logfiles.sh \
			 file://var/www/tpl/navi/system/map.sh \
			 file://var/www/tpl/navi/system/setup.sh \
			 file://var/www/tpl/navi/system/zz_logout.sh \
"

SRC_URI += " file://var/www/tpl/system/10_help.sh \
"

SRC_URI += " file://var/www/tpl/about.sh \
			 file://var/www/tpl/apps.sh \
			 file://var/www/tpl/commands.sh \
			 file://var/www/tpl/errorhandling.sh \
			 file://var/www/tpl/help.sh \
			 file://var/www/tpl/home_layout.sh \
			 file://var/www/tpl/iframe.sh \
			 file://var/www/tpl/info.sh \
			 file://var/www/tpl/logfiles.sh \
			 file://var/www/tpl/login.sh \
			 file://var/www/tpl/logout.sh \
			 file://var/www/tpl/map.sh \
			 file://var/www/tpl/quickstart.sh \
			 file://var/www/tpl/reboot.sh \
			 file://var/www/tpl/setup.js \
			 file://var/www/tpl/setup.sh \
			 file://var/www/tpl/system.sh \
"


SRC_URI += " file://var/www/functions.sh \
			 file://var/www/iframe.css \
			 file://var/www/iframe.sh \
			 file://var/www/index.css \
			 file://var/www/index.js \
			 file://var/www/index.sh \
             file://var/www/.htaccess \
"

#SRC_URI_append_arm = " file://alignment.sh"


do_install_append () {
	#
	# Directorys erstellen
	#
	install -d ${D}/var/spool 
	install -d ${D}${sysconfdir}/applications/register.d
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/setup
	install -d ${D}${bindir}
	install -d ${D}${docdir}/webserver
	install -d ${D}${datadir}/locale/de_DE/LC_MESSAGES
	install -d ${D}${datadir}/locale/en_GB/LC_MESSAGES
	
	install -d ${D}${localstatedir}/www	
	install -d ${D}${localstatedir}/www/images	
	install -d ${D}${localstatedir}/www/tpl/	
	install -d ${D}${localstatedir}/www/tpl/about	
	install -d ${D}${localstatedir}/www/tpl/apps
	install -d ${D}${localstatedir}/www/tpl/commands	
	install -d ${D}${localstatedir}/www/tpl/commands/system	
	install -d ${D}${localstatedir}/www/tpl/index	
	install -d ${D}${localstatedir}/www/tpl/logfiles	
	install -d ${D}${localstatedir}/www/tpl/navi
	install -d ${D}${localstatedir}/www/tpl/navi/services	
	install -d ${D}${localstatedir}/www/tpl/navi/system	
	install -d ${D}${localstatedir}/www/tpl/system	
		
	
	#
	# Dateien installieren
	#
    install -m 0755    ${WORKDIR}/etc/applications/register.d/webserver							${D}${sysconfdir}/applications/register.d
    install -m 0755    ${WORKDIR}/etc/init.d/webserver											${D}${sysconfdir}/init.d
    install -m 0755    ${WORKDIR}/etc/setup/webserver.sh										${D}${sysconfdir}/setup
    install -m 0755    ${WORKDIR}/etc/setup/webserver.xml										${D}${sysconfdir}/setup
    
    install -m 0755    ${WORKDIR}/usr/bin/get_debug_log_collection.sh							${D}${bindir}
    install -m 0755    ${WORKDIR}/usr/bin/update_quick_start.sh									${D}${bindir}
    install -m 0755    ${WORKDIR}/usr/bin/update_web_setup.sh									${D}${bindir}
    install -m 0755    ${WORKDIR}/usr/bin/webserver.sh											${D}${bindir}

    #install -m 0755    ${WORKDIR}/usr/bin/urldecode                                             ${D}${bindir}


    install -m 0755    ${WORKDIR}/usr/share/doc/webserver/copyright								${D}${docdir}/webserver
    install -m 0755    ${WORKDIR}/usr/share/doc/webserver/history								${D}${docdir}/webserver
    
    install -m 0755    ${WORKDIR}/usr/share/locale/de_DE/LC_MESSAGES/webserver-update_quick_start.sh.mo	${D}${datadir}/locale/de_DE/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/de_DE/LC_MESSAGES/webserver-update_web_setup.sh.mo	${D}${datadir}/locale/de_DE/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/de_DE/LC_MESSAGES/webserver-webserver.mo				${D}${datadir}/locale/de_DE/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/de_DE/LC_MESSAGES/webserver-webserver.xml.mo			${D}${datadir}/locale/de_DE/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/de_DE/LC_MESSAGES/webserver-www.mo					${D}${datadir}/locale/de_DE/LC_MESSAGES

    install -m 0755    ${WORKDIR}/usr/share/locale/en_GB/LC_MESSAGES/webserver-update_quick_start.sh.mo	${D}${datadir}/locale/en_GB/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/en_GB/LC_MESSAGES/webserver-update_web_setup.sh.mo	${D}${datadir}/locale/en_GB/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/en_GB/LC_MESSAGES/webserver-webserver.mo				${D}${datadir}/locale/en_GB/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/en_GB/LC_MESSAGES/webserver-webserver.xml.mo			${D}${datadir}/locale/en_GB/LC_MESSAGES
    install -m 0755    ${WORKDIR}/usr/share/locale/en_GB/LC_MESSAGES/webserver-www.mo					${D}${datadir}/locale/en_GB/LC_MESSAGES

    install -m 0755    ${WORKDIR}/var/www/images/bg.jpg										${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/bg_headline.png							${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/bg_navi.png								${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/bg_navi_active.png							${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/favicon.ico								${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_collapse.png							${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_community.png							${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_download.png							${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_expand.png							${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_feed.png								${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_home.png								${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_kind.png								${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_list.png								${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_rss.png								${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/icon_sortorder.png							${D}${localstatedir}/www/images	
    install -m 0755    ${WORKDIR}/var/www/images/logo.png									${D}${localstatedir}/www/images	

    install -m 0755    ${WORKDIR}/var/www/tpl/about/10_about.sh								${D}${localstatedir}/www/tpl/about	
    install -m 0755    ${WORKDIR}/var/www/tpl/about/20_packages.sh							${D}${localstatedir}/www/tpl/about	
    install -m 0755    ${WORKDIR}/var/www/tpl/about/30_mld.sh								${D}${localstatedir}/www/tpl/about	
    install -m 0755    ${WORKDIR}/var/www/tpl/about/40_packages.sh							${D}${localstatedir}/www/tpl/about	

    install -m 0755    ${WORKDIR}/var/www/tpl/commands/system.sh							${D}${localstatedir}/www/tpl/commands	
    install -m 0755    ${WORKDIR}/var/www/tpl/commands/system/poweroff.sh					${D}${localstatedir}/www/tpl/commands/system	
    install -m 0755    ${WORKDIR}/var/www/tpl/commands/system/reboot.sh						${D}${localstatedir}/www/tpl/commands/system	

    install -m 0755    ${WORKDIR}/var/www/tpl/index/10_welcome.sh							${D}${localstatedir}/www/tpl/index	
    install -m 0755    ${WORKDIR}/var/www/tpl/index/15_services.sh							${D}${localstatedir}/www/tpl/index	
    install -m 0755    ${WORKDIR}/var/www/tpl/index/50_quickstart.sh						${D}${localstatedir}/www/tpl/index	
    install -m 0755    ${WORKDIR}/var/www/tpl/index/90_errorhandling.sh						${D}${localstatedir}/www/tpl/index	

    install -m 0755    ${WORKDIR}/var/www/tpl/logfiles/messages.sh							${D}${localstatedir}/www/tpl/logfiles	
    install -m 0755    ${WORKDIR}/var/www/tpl/logfiles/sysinit.sh							${D}${localstatedir}/www/tpl/logfiles	
    install -m 0755    ${WORKDIR}/var/www/tpl/logfiles/zz_errorhandling.sh					${D}${localstatedir}/www/tpl/logfiles	

    install -m 0755    ${WORKDIR}/var/www/tpl/navi/services/apps.sh							${D}${localstatedir}/www/tpl/navi/services	
    install -m 0755    ${WORKDIR}/var/www/tpl/navi/system/commands.sh						${D}${localstatedir}/www/tpl/navi/system	
    install -m 0755    ${WORKDIR}/var/www/tpl/navi/system/info.sh							${D}${localstatedir}/www/tpl/navi/system	
    install -m 0755    ${WORKDIR}/var/www/tpl/navi/system/logfiles.sh						${D}${localstatedir}/www/tpl/navi/system	
    install -m 0755    ${WORKDIR}/var/www/tpl/navi/system/map.sh							${D}${localstatedir}/www/tpl/navi/system	
    install -m 0755    ${WORKDIR}/var/www/tpl/navi/system/setup.sh							${D}${localstatedir}/www/tpl/navi/system	
    install -m 0755    ${WORKDIR}/var/www/tpl/navi/system/zz_logout.sh						${D}${localstatedir}/www/tpl/navi/system	

    install -m 0755    ${WORKDIR}/var/www/tpl/system/10_help.sh								${D}${localstatedir}/www/tpl/system	

	install -m 0755    ${WORKDIR}/var/www/tpl/about.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/apps.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/commands.sh									${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/errorhandling.sh								${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/help.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/home_layout.sh								${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/iframe.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/info.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/logfiles.sh									${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/login.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/logout.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/map.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/quickstart.sh									${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/reboot.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/setup.js										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/setup.sh										${D}${localstatedir}/www/tpl	
	install -m 0755    ${WORKDIR}/var/www/tpl/system.sh										${D}${localstatedir}/www/tpl	

    install -m 0755    ${WORKDIR}/var/www/functions.sh										${D}${localstatedir}/www	
    install -m 0755    ${WORKDIR}/var/www/iframe.css										${D}${localstatedir}/www	
    install -m 0755    ${WORKDIR}/var/www/iframe.sh											${D}${localstatedir}/www	
    install -m 0755    ${WORKDIR}/var/www/index.css											${D}${localstatedir}/www	
    install -m 0755    ${WORKDIR}/var/www/index.js											${D}${localstatedir}/www	
    install -m 0755    ${WORKDIR}/var/www/index.sh											${D}${localstatedir}/www	
    install -m 0755    ${WORKDIR}/var/www/.htaccess                                         ${D}${localstatedir}/www    

	
}

#FILES_${PN} += " /*"

FILES_${PN} += " \
       ${localstatedir} \
       /etc/init.d/webserver \
       /var/spool \
       /var/spool/error \
	   /var/spool/bootstep \
       /var/www/functions.sh \
	   /var/www/iframe.css \
	   /var/www/iframe.sh \
	   /var/www/index.css \
	   /var/www/index.js \
	   /var/www/index.sh \
	   /var/www/.htaccess \
	   /var/www/tpl/about.sh \
	   /var/www/tpl/apps.sh \
	   /var/www/tpl/commands.sh \
	   /var/www/tpl/errorhandling.sh \
	   /var/www/tpl/help.sh \
	   /var/www/tpl/home_layout.sh \
	   /var/www/tpl/iframe.sh \
	   /var/www/tpl/info.sh \
	   /var/www/tpl/logfiles.sh \
	   /var/www/tpl/login.sh \
	   /var/www/tpl/logout.sh \
	   /var/www/tpl/map.sh \
	   /var/www/tpl/quickstart.sh \
	   /var/www/tpl/reboot.sh \
	   /var/www/tpl/setup.js \
	   /var/www/tpl/setup.sh \
	   /var/www/tpl/system.sh \
       "
       