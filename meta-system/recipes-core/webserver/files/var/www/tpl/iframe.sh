<script>
	function expandIframe(name)
	{
		window.open(window.frames["iframe_"+name].window.location.href, "_"+window.frames["iframe_"+name].window.location.search.replace(/.*site=([^&]*).*|.*/, "\$1"));
	}
	function setHeadline(element)
	{
		var h1 = element.parentNode.parentNode.parentNode.getElementsByTagName("h1")[0];
		var h1New = window.frames[element.name].document.getElementsByTagName("h1")[0];
		if(h1New) {
			h1.innerHTML = h1New.innerHTML;
			h1New.parentNode.removeChild(h1New);
		}
		h1.onclick = function () {
			window.frames[element.name].window.location.href = element.src;
		}
		h1.style.cursor = "pointer";
	}
</script>

<div class="icon expand" onclick="expandIframe('$GET_site')"></div>

<div class="iframe">
	<iframe onload="setHeadline(this)" src="/?$query" name="iframe_$GET_site"></iframe>
</div>
