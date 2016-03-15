<? 
. functions.sh

session_start 

path=${request_uri:-home}

?>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title>$HOST_NAME@Home</title>

	<link href="/images/favicon.ico" rel="shortcut icon">
	<link href="/images/favicon.ico" rel="icon" type="image/ico">

	<link rel="alternate" type="application/rss+xml" href="$MLD_URL/rss.php?section=news&id=42" title="MLD news">
	<link rel="alternate" type="application/rss+xml" href="$MLD_URL/rss.php?section=forum" title="MLD Forum news">
	<link rel="alternate" type="application/rss+xml" href="$MLD_URL/rss.php?section=updates&version=$SYSTEM_VERSION" title="MLD Update news">
	
	<link href="/index.css" rel="stylesheet" type="text/css">

	<script src="/index.js" type="text/javascript"></script>
	
</head>
<body class="$HTTP_Cookie_fullscreen">

<div id="header">
	<a href="/"><img src="images/logo.png"></a>
</div>

<div id="body">
	<? if [ -z "${HTTP_UserAgent##NetSurf*}" ]; then ?>
		<img class="background" src="images/bg.jpg">
	<? fi ?>

	<ul class="navi">
		<li class="<? test "${path%%/""*}" = "home" && echo -n "active" ?>"><a href="/home">$(TEXTDOMAIN="webserver-www" gt 'Home')</a></li>
		<li class="<? test "${path%%/""*}" = "packages" && echo -n "active" ?>"><a href="/packages">$(TEXTDOMAIN="webserver-www" gt 'Packages')</a></li>
		<li class="<? test "${path%%/""*}" = "system" && echo -n "active" ?>"><a href="/system">$(TEXTDOMAIN="webserver-www" gt 'System')</a></li>
		<div class="expand" onclick="expandBody()"></div>
	</ul>

	<div class="frame">
		<? if [ "${path%%/""*}" = "home" ]; then ?>
			<div class="size_1_3">
				<div>
					<div>
						<h1>$(TEXTDOMAIN="webserver-www" gt 'Welcome')</h1>
						<div class="scroll">
							<div class="text">
								<? include "tpl/index/"*.sh ?>
							</div>
						</div>
					</div>
				</div>
			</div>
			<? if [ -f "tpl/${GET_site}_layout.sh" ]; then ?>
				<? include "tpl/${GET_site}_layout.sh" ?>
			<? elif [ -n "$GET_site" ]; then ?>
				<div class="size_1_3">
					<div>
						<div>
							<h1></h1>
							<div class="scroll">
								<? include "tpl/iframe.sh" ?>
							</div>
						</div>
					</div>
				</div>
			<? fi ?>
		<? fi ?>

		<? if [ "${path%%/""*}" = "system" ]; then ?>
			<div class="size_1_4">
				<div>
					<div>
						<h1>$(TEXTDOMAIN="webserver-www" gt 'System')</h1>
						<div class="scroll">
							<div class="text">
								<ul>
									<? include "tpl/navi/system/"*.sh ?>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<? if [ "$GET_site" = "setup" -o "$GET_site" = "quickstart" ]; then ?>
				<div class="size_1_2">
					<div>
						<div>
							<h1></h1>
							<div class="scroll">
								<? include "tpl/iframe.sh" ?>
							</div>
						</div>
					</div>
				</div>
				<div class="size_1_4">
					<div>
						<div>
							<h1>$(TEXTDOMAIN="webserver-www" gt 'Help')</h1>
							<div class="scroll">
								<div class="text">
									<? include "tpl/help.sh" ?>
								</div>
							</div>
						</div>
					</div>
				</div>
			<? else ?>
				<div class="size_3_4">
					<div>
						<div>
							<h1></h1>
							<div class="scroll">
								<? query=${query:-site=system} ?>
								<? include "tpl/iframe.sh" ?>
							</div>
						</div>
					</div>
				</div>
			<? fi ?>
		<? fi ?>

		<? if [ "${path%%/""*}" = "about" ]; then ?>
			<div class="size_1_4">
				<div>
					<div>
						<h1>$(TEXTDOMAIN="webserver-www" gt 'About')</h1>
						<div class="scroll">
							<div class="text">
								<? include "tpl/about/"*.sh ?>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="size_3_4">
				<div>
					<div>
						<h1></h1>
						<div class="scroll">
							<? query="${query:-site=about&name=MLD about&file=/boot/ABOUT}" ?>
							<? GET_site="about" ?>
							<? include "tpl/iframe.sh" ?>
						</div>
					</div>
				</div>
			</div>
		<? fi ?>

		<? if [ -f "tpl/${path%%/""*}/index.sh" ]; then ?>
			<? include "tpl/${path%%/""*}/index.sh" ?>
		<? fi ?>
	</div>
</div>

<div id="footer">
	<div>
		<div class="left">
			<ul>
				<li><a href="$MLD_URL"><img src="/images/icon_home.png"></a></li>
				<li><a href="$MLD_URL/download/$SYSTEM_VERSION"><img src="/images/icon_download.png"></a></li>
				<li><a href="$MLD_URL/community/forum"><img src="/images/icon_community.png"></a></li>
				<li><a href="$MLD_URL/rss.php?section=news&id=42" target="rss" id="rss_button"><img src="/images/icon_rss.png"></a></li>
			</ul>
		</div>
		<div class="center">
			All Rights Reserved! Copyright © 2000-<? date +%Y ?> by MiniDVBLinux™
		</div>
		<div class="right">
			<ul>
				<li><a href="/about">$(TEXTDOMAIN="webserver-www" gt 'about')</a></li>
			</ul>
		</div>
	</div>
</div>

</body>
</html>
