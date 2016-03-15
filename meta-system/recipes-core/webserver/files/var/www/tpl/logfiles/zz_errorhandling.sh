<?
if [ "$GET_download" ]; then
	. /etc/rc.config
	header "Content-Type: application/x-compressed-tar"
	header "Content-Disposition: filename=`date +%Y-%m-%d_%H%M_$SYSTEM_TYPE_$HOST_NAME`_debug_log.tgz"
	/usr/bin/get_debug_log_collection.sh /tmp/DEBUG_LOG_$$.tgz >/dev/null 2>&1
	cat /tmp/DEBUG_LOG_$$.tgz
	rm /tmp/DEBUG_LOG_$$.tgz
	exit
fi
?>

<iframe name="download" style="display:none;"></iframe>
<br>
<li><a href="/tpl/logfiles/zz_errorhandling.sh?download=1" target="download">$(TEXTDOMAIN="webserver-www" gt "Download DEBUG Logs")</a></li>
<li><a href="?site=errorhandling">$(TEXTDOMAIN="webserver-www" gt "Upload DEBUG Logs")</a></li>
