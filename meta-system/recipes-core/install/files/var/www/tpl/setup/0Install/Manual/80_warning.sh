<script>
document.forms['setup']['INSTALL_FORMAT_DATA_PARTITION'].onchange = function () {
	document.getElementById("install_format_data_partition_warning").style.display = this.checked ? "block" : "";
	store(this);
}
</script>

<style>
#body #install_format_data_partition_warning {
	display: none;
	margin-top: 15px;
	color: red;
}
</style>

<p id="install_format_data_partition_warning" class="row">$(gt 'The whole selected data partition will be deleted')</p>
