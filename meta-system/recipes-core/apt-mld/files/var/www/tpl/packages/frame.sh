<script>
var requests = [];

function action(action, name)
{
	requests[name] = new request (name, action);
}

function restartVDR()
{
	action("restartVDR", "restartVDR");
}

function request(name, action)
{
	var div = document.createElement("div");
	parent.document.getElementById('status').appendChild(div);
	parent.document.getElementById('status_head').style.display = "block";
	
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 3) {
			div.innerHTML = request.responseText;
			div.parentNode.parentNode.parentNode.scrollTop = div.parentNode.parentNode.parentNode.scrollHeight;
		}
		if (request.readyState == 4) {
			div.style.opacity = 0.5;
			if (request.responseText.search("Setting up vdr-") != -1 || request.responseText.search("Removing vdr-") != -1) {
				var restartVdr = parent.document.getElementById('status_restart_vdr')
				restartVdr.style.display = "block";
				restartVdr.onclick = restartVDR;
				div.parentNode.parentNode.parentNode.scrollTop = div.parentNode.parentNode.parentNode.scrollHeight;
			}
		}
	}
	request.open("GET", "/tpl/packages/action.sh?action="+action+"&package="+name);
	request.send();
}

function setView (name, value)
{
	setCookie(name, value, 365);
	document.location.reload();
}

function setCookie(name, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	document.cookie = name + "=" + encodeURI(value) + "; path=/" + ((exdays === undefined) ? "" : "; expires=" + exdate.toUTCString());
}
</script>

<? if [ -n "$GET_section" ]; then ?>
	<? include "tpl/packages/$GET_section.sh" ?>
<? fi ?>
