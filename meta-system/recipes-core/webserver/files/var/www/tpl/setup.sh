<h1>$(TEXTDOMAIN="webserver-www" gt 'Setup your system')</h1>

<?
include tpl/login.sh 
login || return

section=$GET_section
if [ "$POST_action" = "save" ]; then
	for name in $POST_changed; do
		eval "value=\"\$POST_${name}\""
		test "$value" = "-" && value=""
		update_setting $name "$value"
	done
	for name in $POST_changed; do
		eval "command=\"\$POST_${name}_command\""
		test -n "$command" && eval "$command" 2>&1 | weblog
	done
	test -n "$POST_changed" -a -n "$POST_command" && $POST_command 2>&1 | weblog
elif [ "$POST_action" = "reboot" ]; then
	rebootURL="$POST_params"
	include tpl/reboot.sh
elif [ -n "$POST_action" ]; then
	$POST_action 2>&1 | weblog
fi

. /etc/rc.config
?>

<script src="tpl/setup.js"></script>

<form name="setup" method="post" action="">
	<? if [ -z "$(ls tpl/setup 2>/dev/null)" -o ! -e /var/spool/setup.ls -o "$(cat /var/spool/setup.ls 2>/dev/null)" != "$(ls -l tpl/setup.d /etc/setup)" ]; then ?>
		<? ls -l tpl/setup.d /etc/setup > /var/spool/setup.ls ?>
		<? update_web_setup.sh &>/var/log/sysinit & ?>
	<? fi ?>
	<? if ps -w | grep -v grep | grep -q update_web_setup.sh; then ?>
		<p id="wait">$(TEXTDOMAIN="webserver-www" gt 'Preparing setup. Please wait or try again later...')</p>
		<? while ps -w | grep -v grep | grep -q update_web_setup.sh; do sleep 1; done ?>
		<style>#wait{display:none;}</style>
	<? fi ?>

	<? if ls tpl/setup/*.sh &>/dev/null; then ?>
		<? include "tpl/setup/$section/"*.sh ?>
	<? else ?>
		<p>$(TEXTDOMAIN="webserver-www" gt 'No setup available.')</p>
	<? fi ?>

	<input type="hidden" name="action" value="save"/>
	<input type="hidden" name="params" value=""/>
	<input type="hidden" name="changed" value=""/>
</form>

<script>initSelects (); initHelp ()</script>
