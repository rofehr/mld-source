<h1>$(TEXTDOMAIN="webserver-www" gt 'Quick start')</h1>

<?
include tpl/login.sh
login || return

if [ "$POST_action" = "save" ]; then
	for name in $POST_changed; do
		eval "value=\"\$POST_${name}\""
		test "$value" = "-" && value=""
		update_setting $name "$value"
	done
	bases=" "
	for name in $POST_changed; do
		eval "command=\"\$POST_${name}_command\""
		test -n "$command" && eval "$command" 2>&1 | weblog
		test "${bases##* ${name%%_*} *}" && bases="$bases${name%%_*} "
	done
	for base in $bases; do
		eval "command=\"\$POST_command_${base}\""
		test -n "$command" && eval "$command" 2>&1 | weblog
	done
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
	<? if [ -z "$(ls tpl/quickstart 2>/dev/null)" -o ! -e /var/spool/quickstart.ls -o "$(cat /var/spool/quickstart.ls 2>/dev/null)" != "$(ls -l tpl/quickstart.d /etc/setup)" ]; then ?>
		<? ls -l tpl/quickstart.d /etc/setup > /var/spool/quickstart.ls ?>
		<? update_quick_start.sh &>/var/log/sysinit & ?>
	<? fi ?>	
	<? if ps -w | grep -v grep | grep -q update_quick_start.sh; then ?>
		<p id="wait">$(TEXTDOMAIN="webserver-www" gt 'Preparing quickstart. Please wait or try again later...')</p>
		<? while ps -w | grep -v grep | grep -q update_quick_start.sh; do sleep 1; done ?>
		<style>#wait{display:none;}</style>
	<? fi ?>

	<? if ls tpl/quickstart/*.sh &>/dev/null; then ?>
		<? eval include $(find tpl/quickstart -type f | sort | sed "s/ /\\\\ /") ?>
	<? else ?>
		<p>$(TEXTDOMAIN="webserver-www" gt 'No quickstart available.')</p>
	<? fi ?>

	<input type="hidden" name="action" value="save"/>
	<input type="hidden" name="params" value=""/>
	<input type="hidden" name="changed" value=""/>
</form>

<script>initSelects (); initHelp ("$(TEXTDOMAIN="webserver-www" gt 'Setup')")</script>
