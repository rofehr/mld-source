<h1>$(gt 'Upgrades')</h1>

<?
include tpl/login.sh
login || return
?>

<script>action('update', '');</script>
<?
sleep 2
packages="$(lock apt-get -s dist-upgrade | grep '^Inst' | sed "s/Inst \([^: ]*\)[^ ]* \(\[\([^ ]*\)\] \)\?(\([^ ]*\).*\[\(.*\)\].*/\1 \3 \4 \5/" | sort -u)" #"
?>

<div class="icon view" title="$(gt 'view')">
	<select onchange="iframe_upgrade.setView('upgrade_view', this.value)">
		<optgroup label="$(gt 'view')">
			<option value="short" <? test "$HTTP_Cookie_upgrade_view" = "short" && echo "selected" ?>>$(gt "short")</option>
			<option value="full" <? test "$HTTP_Cookie_upgrade_view" = "full" && echo "selected" ?>>$(gt "full")</option>
		</optgroup>
	</select>
</div>

<? if [ -z "$packages" ]; then ?>
	<p>$(gt 'No upgrades available')</p>
	<? return ?>
<? fi ?>

<ul class="download">
	<li>
		<input type="checkbox" value="1" title="$(gt 'Upgrade')" onclick="action('upgrade', ''); this.disabled=true;">
		<h2>$(gt 'All packages')</h2>
		<p>
			$(gt 'Upgrade all packages')
		</p>
	</li>
	<? test "$packages" && echo "$packages" | while read package oldVersion newVersion arch; do ?>
		<? test "$HTTP_Cookie_upgrade_view" != "full" && test -z "${package##lib*}" && continue ?>
		<li>
			<input type="checkbox" value="1" title="$(gt 'Upgrade')" onclick="action('upgrade', '${package#-}'); this.disabled=true;" <? test "${package##-*}" || echo "disabled" ?>>
			<h2>${package#-}</h2>
			<p><a href="$MLD_URL/download/$SYSTEM_VERSION/$APT_PACKAGE_CLASS/$arch/docs/$package/history">$oldVersion => $newVersion</a></p>
		</li>
	<? done ?>
</ul>
