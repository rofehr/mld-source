var rssFeed = "";

window.onload = function() {
	var elements = document.getElementsByClassName("icon");
	for (var i=0; i<elements.length; i++) {
		elements[i].parentNode.parentNode.insertBefore(elements[i], elements[i].parentNode.parentNode.firstChild);
	}
	
	if (rssFeed) {
		document.getElementById("rss_button").href = rssFeed;
	}
};

if(!document.getElementsByClassName) {
    document.getElementsByClassName = function(className) {
        return this.querySelectorAll("." + className);
    };
    Element.prototype.getElementsByClassName = document.getElementsByClassName;
}

function expandBody() {
	if (document.body.className == "") {
		document.body.className = "fullscreen";
	} else {
		document.body.className = "";
	}
	setCookie("fullscreen", document.body.className, 7);
}

function setCookie(name, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	document.cookie = name + "=" + encodeURI(value) + "; path=/" + ((exdays === undefined) ? "" : "; expires=" + exdate.toUTCString());
}
function delCookie(name) {
	document.cookie = name + "=; path=/; expires=" + new Date().toUTCString();
}

function ajax(url, data, onready)
{
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			var response = "";
			try {
				response = eval("("+xmlhttp.responseText+")");
			} catch (e) {};
			onready(response);
		}
	}
	xmlhttp.open("POST", url, true);
	if (data) {
		var values = [];
		for (key in data) {
		    values.push(key + '=' + encodeURIComponent(data[key]));
		}
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.send(values.join('&'));
	} else {
		xmlhttp.send();
	}
}
