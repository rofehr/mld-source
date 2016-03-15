<h1>$(TEXTDOMAIN="webserver-www" gt 'Show logfiles of your system')</h1>

<ul class='row navi'>
	<? include tpl/logfiles/*.sh ?>
</ul>

<? if [ -n "$GET_file" ]; then ?>
	<h3>/var/log/$GET_file</h3>
	<? cat /var/log/$GET_file | weblog ?>
<? fi ?>
