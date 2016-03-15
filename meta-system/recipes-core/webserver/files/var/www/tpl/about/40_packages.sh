<ul>
	<? for name in $(cd /usr/share/doc/; ls); do ?>
		<li>
			$name:<br>
			<? for file in $(cd /usr/share/doc/$name; ls); do ?>
				<a href="/?site=about&name=$name $file&file=/usr/share/doc/$name/$file" target="iframe_about">$file</a><br>
			<? done ?>
		</li>
	<? done ?>
</ul>
