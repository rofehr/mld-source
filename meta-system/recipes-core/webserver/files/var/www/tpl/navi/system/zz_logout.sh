<? if chkpasswd "root" "${SESSION_password}" 2>/dev/null; then ?>
	<hr>
	<li class="contact"><a href="/?site=logout">$(TEXTDOMAIN="webserver-www" gt 'Logout')</a></li>
<? fi ?>