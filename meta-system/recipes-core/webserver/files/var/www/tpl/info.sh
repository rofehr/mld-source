<style>
.active {
  font-weight: bold;
}
.installed {
  font-weight: bold;
  font-style: italic;
}
table {
	border: 0;
	border-spacing: 0;
	border-collapse: collapse;
}
th {
	margin: 20px 0 5px 0;
	padding: 0 20px 3px 0;
	font-size: 1.2em;
	text-align: left;
	color: #000000;
}
td {
	padding: 0 20px 3px 0;
}
</style>

<h1>$(TEXTDOMAIN="webserver-www" gt 'System information')</h1>

<h2>System</h2>
<table>
	<tr>
		<td>Name:</td>
		<td>$HOST_NAME</td>
	</tr>
	<tr>
		<td>Version:</td>
		<td>$SYSTEM_VERSION</td>
	</tr>
</table>

<h2></h2>
<table>
	<tr>
		<th>Package</td>
		<th>Version</td>
	</tr>
	<? apt list --installed 2>/dev/null | grep -v ^Listing... | grep -v ^lib | sed "s|\(.*\)/[^ ]* \([^ ]*\) .*|\1 \2|" | while read package version; do ?>
		<tr>
			<td>$package</td>
			<td>$version</td>
		</tr>
	<? done ?>
</table>

<h2>Disks</h2>
<pre><? df -h | grep -e "Mounted on" -e "^/dev/.*/$" -e "/mnt/" -e "/media/" | sed "s|\(.*Mounted on.*\)|<strong>\1</strong>|" ?></pre>

<h2>Modules</h2>
<pre><? lsmod ?></pre>

<h2>Status</h2>
<pre><? top -b -n1 | head -n2 ?></pre>

<h2>Network</h2>
<pre><? ifconfig ?></pre>
