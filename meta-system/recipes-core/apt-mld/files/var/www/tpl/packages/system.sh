<h1>$(gt 'System packages')</h1>

<?
include tpl/login.sh
login || return
?>

<?
packages="$(while read arch; do apt-cache -g show .:$arch; done < /var/lib/dpkg/arch | sed -n "s/^\(Package\|Section\|Description\): \(.*\)/\2/p;"| sed "N;s/\n/ /; N;s/\n/ /" | grep -v "^\S* libs " | grep -v ^vdr-plugin- | sort -u -k $(test "$HTTP_Cookie_system_order" = "group" && echo 2 || echo 1))" #"
xorg_driver_installed="$(dpkg --get-selections | grep ^xorg- | grep -v autodetect | cut -d" " -f1)"
?>

<div class="icon sortorder" title="$(gt 'sort order')">
	<select onchange="iframe_system.setView('system_order', this.value)">
		<optgroup label="$(gt 'sort order')">
			<option value="name" <? test "$HTTP_Cookie_system_order" = "name" && echo "selected" ?>>$(gt "name")</option>
			<option value="group" <? test "$HTTP_Cookie_system_order" = "group" && echo "selected" ?>>$(gt "group")</option>
		</optgroup>
	</select>
</div>
<div class="icon view" title="$(gt 'view')">
	<select onchange="iframe_system.setView('system_view', this.value)">
		<optgroup label="$(gt 'view')">
			<option value="short" <? test "$HTTP_Cookie_system_view" = "short" && echo "selected" ?>>$(gt "short")</option>
			<option value="full" <? test "$HTTP_Cookie_system_view" = "full" && echo "selected" ?>>$(gt "full")</option>
		</optgroup>
	</select>
</div>

<? if [ "$HTTP_Cookie_system_order" = "group" ]; then ?>
	<ul class="group">
		<? test "$packages" && echo "$packages" | cut -d" " -f 2 | sort -u | while read group; do ?>
			<? test "$group" = "libs" && continue ?>
			<? test "$HTTP_Cookie_system_view" != "full" && test "$group" = "system" && continue ?>
			<li>
				<a href="#$group">$group</a>
			</li>
		<? done ?>
	</ul>
<? fi ?>

<ul class="download">
	<? test "$packages" && echo "$packages" | while read package group description; do ?>
		<? test "$HTTP_Cookie_system_view" != "full" && test "$group" = "system" && continue ?>
		<? test "$HTTP_Cookie_system_view" != "full" && test "$xorg_driver_installed" && test "$package" != "$xorg_driver_installed" && echo "$package" | grep -q ^xorg- && continue ?>
		<? if [ "$HTTP_Cookie_system_order" = "group" -a "$last_group" != "$group" ]; then ?>
			<? if [ "$last_group" ]; then ?>
				<hr>
			<? fi ?>
			<li class="group">
				<a name="$group"></a>
				<h2>$group</h2>
			</li>
			<? last_group=$group ?>
		<? fi ?>
		<li>
			<input type="checkbox" name="$package" value="1" title="$(gt 'Un/install')" onclick="action(this.checked ? 'install' : 'remove', this.name)">
			<h2>$package</h2>
			<p>$description</p>
		</li>
	<? done ?>
</ul>

<script>
var installed = "<?=$(dpkg --get-selections | grep -v deinstall | grep -v ^vdr-plugin- | cut -f1)?>".split(" ");
for (i in installed) {
	if (installed[i] && document.getElementsByName(installed[i])[0]) {
		document.getElementsByName(installed[i])[0].checked = true;
	}
}
</script>