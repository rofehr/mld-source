<script>
function waitForReboot()
{
	window.setTimeout(function() {
		httpobj = new XMLHttpRequest();
		httpobj.open ("GET", "/", true);
		httpobj.onreadystatechange = function() {
			if (httpobj.readyState == 4) {
				if (httpobj.status == 200) {
					top.document.location.href='$rebootURL';
				} else {
					waitForReboot()
				}
			}
		}
		httpobj.send();
	}, 2000);
}
waitForReboot()
</script>

<p>$(TEXTDOMAIN="webserver-www" gt 'Waiting for reboot...')</p>
<?
reboot
stop webserver &>/dev/null
sleep 30
start webserver &>/dev/null
?>
<p>$(TEXTDOMAIN="webserver-www" gt 'A problem occurred. The system do not reboot')</p>
<br>
