<? 
login()
{
	test -n "$POST_password" && session_set "password" "$POST_password" 
	if [ "$WEBSERVER_PASSWORD_CHECK" ] && ! chkpasswd "root" "$SESSION_password" 2>/dev/null; then 
		?>
		<h2>$(TEXTDOMAIN="webserver-www" gt 'Login')</h2>
		<form name="setup" method="post" action="">
		<? if [ -n "$POST_password" ]; then ?>
			<p class="error">$(TEXTDOMAIN="webserver-www" gt 'Wrong password')</p>
		<? fi ?>
		<div class='row'>
			<label title="$(TEXTDOMAIN="webserver-www" gt 'Enter your root password')">$(TEXTDOMAIN="webserver-www" gt 'Password'):</label>
			<input type="password" name="password" value="" />
			<script>document.setup.password.focus();</script>
		</div>
		<div class="button"><input type="submit" value="$(TEXTDOMAIN="webserver-www" gt 'Login')"/></div>
		</form>
		<?
		return 1
	fi
}
?>
