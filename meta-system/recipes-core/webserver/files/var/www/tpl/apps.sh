<h1>$(TEXTDOMAIN="webserver-www" gt 'Starting an App')</h1>

<p>$(TEXTDOMAIN="webserver-www" gt 'Apps are programms, that are shown on the screen.')</p>

<? if ls tpl/apps/*.sh &>/dev/null; then ?>
	<ul class='row navi'>
		<? include tpl/apps/*.sh ?>
	</ul>
<? else ?>
	<p>$(TEXTDOMAIN="webserver-www" gt 'No Apps installet')</p>
<? fi ?>

<? if [ -n "$GET_app" ]; then ?>
	<p>Starting App $GET_app ...</p>
	<? 
	app="$(grep "^$GET_app " /usr/share/applications | sed "s/\S* //")"
	if [ -x /usr/bin/startapp ]; then
		startapp $app
	else
		eval "DISPLAY=:0 $app" -n >>/var/log/sysinit 2>&1 &
	fi
	?>
<? fi ?>
