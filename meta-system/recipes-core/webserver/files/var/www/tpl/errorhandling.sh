<h1>$(TEXTDOMAIN="webserver-www" gt 'Error handling')</h1>

<p>$(TEXTDOMAIN="webserver-www" gt 'Error handling description')</p>

<br>
<ul>
<li><a href="?site=errorhandling&upload=1">$(TEXTDOMAIN="webserver-www" gt "Upload DEBUG Logs")</a></li>
</ul>
<br>

<p>
<?
if [ "$GET_upload" ]; then
	/usr/bin/get_debug_log_collection.sh /tmp/DEBUG_LOG_$$.tgz >/dev/null 2>&1
	code=$(wget "$MLD_URL/site/log.php" -O - -q --post-file /tmp/DEBUG_LOG_$$.tgz)
	rm /tmp/DEBUG_LOG_$$.tgz
	echo $(TEXTDOMAIN="webserver-www" gt 'Your upload code is: $code')
fi
?>
</p>
