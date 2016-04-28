<h1>$(gt 'VDR plugins')</h1>

<?
include tpl/login.sh
login || return
?>

<?
packages="$(while read arch; do apt-cache -g show ^vdr-plugin-:$arch; done < /var/lib/dpkg/arch | sed -n "s/^\(Package\|Section\|Description\): \(.*\)/\2/p;"| sed "N;s/\n/ /; N;s/\n/ /" | sort -u -k $(test "$HTTP_Cookie_vdr_order" = "group" && echo 2 || echo 1))" #"
wiki="<a href='http://www.vdr-wiki.de/wiki/index.php/Kategorie:Plugins' target='wiki'>Wiki</a>"
?>

<div class="icon sortorder" title="$(gt 'sort order')">
	<select onchange="iframe_vdr.setView('vdr_order', this.value)">
		<optgroup label="$(gt 'sort order')">
			<option value="name" <? test "$HTTP_Cookie_vdr_order" = "name" && echo "selected" ?>>$(gt "name")</option>
			<option value="group" <? test "$HTTP_Cookie_vdr_order" = "group" && echo "selected" ?>>$(gt "group")</option>
		</optgroup>
	</select>
</div>

<? if [ "$HTTP_Cookie_vdr_order" = "group" ]; then ?>
	<ul class="group">
		<? test "$packages" && echo "$packages" | cut -d" " -f 2 | sort -u | while read group; do ?>
			<li>
				<a href="#$group">$group</a>
			</li>
		<? done ?>
	</ul>
<? fi ?>

<p>$(gt 'Get more info about the plugins in the $wiki')</p>
<ul class="download">
	<? test "$packages" && echo "$packages" | while read package group description; do ?>
		<? if [ "$HTTP_Cookie_vdr_order" = "group" -a "$last_group" != "$group" ]; then ?>
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
			<h2>${package#vdr-plugin-}</h2>
			<p>$description</p>
		</li>
	<? done ?>
</ul>

<script>
var installed = "<?=$(dpkg --get-selections | grep -v deinstall | grep ^vdr-plugin- | cut -f1)?>".split(" ");
for (i in installed) {
	if (installed[i] && document.getElementsByName(installed[i])[0]) {
		document.getElementsByName(installed[i])[0].checked = true;
	}
}
</script>
