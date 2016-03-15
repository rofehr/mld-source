<h1>$(TEXTDOMAIN="webserver-www" gt 'Call system commands')</h1>

<?
include tpl/login.sh
login || return
?>

<ul class='row navi'>
	<? include "tpl/commands/$GET_section/"*.sh ?>
</ul>

<? 
if [ -e /tmp/webserver.command ]; then
	sh /tmp/webserver.command 2>&1 | weblog
	rm /tmp/webserver.command
fi
if [ -n "$GET_command" ]; then
	echo "$GET_command" > /tmp/webserver.command
	echo "<script>window.location.href='?site=commands&section=$GET_section'</script>"
fi
?>
