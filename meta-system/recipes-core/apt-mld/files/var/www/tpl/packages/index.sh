<style>
#status_head {
	display: none;
}
#status_restart_vdr {
	display: none;
	font-weight: bold;
	text-align: right;
	cursor: pointer;
}
</style>

<div class="size_1_4">
	<div>
		<div>
			<h1>$(gt 'Packages')</h1>
			<div class="scroll">
				<div class="text">
					<? include tpl/login.sh ?>
					<? if login; then ?>
						<h2 id="status_head">$(gt 'Status')</h2>
						<div id="status"></div>
						<a id="status_restart_vdr">$(gt 'Restart VDR')</a>
					<? fi ?>
				</div>
			</div>
		</div>
	</div>
</div>
<? if login > /dev/null; then ?>
	<div class="size_1_4">
		<div>
			<div>
				<h1></h1>
				<div class="scroll">
					<? query="site=packages/frame&section=system" ?>
					<? GET_site="system" ?>
					<? include "tpl/iframe.sh" ?>
				</div>
			</div>
		</div>
	</div>
	<div class="size_1_4">
		<div>
			<div>
				<h1></h1>
				<div class="scroll">
					<? query="site=packages/frame&section=vdr" ?>
					<? GET_site="vdr" ?>
					<? include "tpl/iframe.sh" ?>
				</div>
			</div>
		</div>
	</div>
	<div class="size_1_4">
		<div>
			<div>
				<h1></h1>
				<div class="scroll">
					<? query="site=packages/frame&section=upgrade" ?>
					<? GET_site="upgrade" ?>
					<? include "tpl/iframe.sh" ?>
				</div>
			</div>
		</div>
	</div>
<? fi ?>
