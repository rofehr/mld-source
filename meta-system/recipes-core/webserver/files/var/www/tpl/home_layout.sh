<? if [ -n "$GET_site" ]; then ?>
	<div class="size_1_2">
		<div>
			<div>
				<h1></h1>
				<div class="scroll">
					<script>
						function setHeadline(element)
						{
							var h1 = element.parentNode.parentNode.parentNode.getElementsByTagName("h1")[0];
							var h1New = window.frames[element.name].document.getElementsByTagName("h1")[0];
							if(h1New) {
								h1.innerHTML = h1New.innerHTML;
								h1New.parentNode.removeChild(h1New);
							}
							h1.onclick = function () {
								window.frames[element.name].window.location.href = element.src.replace(/\?.*/, "");
							}
							h1.style.cursor = "pointer";
						}
					</script>
					
					<div class="iframe">
						<iframe onload="setHeadline(this)" src="/?site=${GET_site-system}" name="iframe_system"></iframe>
					</div>

				</div>
			</div>
		</div>
	</div>
<? fi ?>
