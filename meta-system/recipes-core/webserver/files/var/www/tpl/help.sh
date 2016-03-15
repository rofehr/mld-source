<script>
var lang = "${LANG%%_*}"

function requestHelp(url, path, onready)
{
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			onready(path, xmlhttp.responseText);
		}
	}
	xmlhttp.open("GET", url + path + "?do=export_xhtmlbody", true);
	xmlhttp.send();
}

function showHelp(sections)
{
	var article = document.getElementById("article");
	var help = document.getElementById("help");
	help.innerHTML = "";

	for (var path in sections) {
		var section = document.createElement("div");
		section.id = "section_" + path.replace(/\//g, "_");
		help.appendChild(section);

		var wikiUrl = "http://www.minidvblinux.de/wiki/" + lang + "/";
		requestHelp(wikiUrl, path, function(path, response) {
			article.innerHTML = response.replace(/h3/g, "h4").replace(/h2/g, "h3").replace(/h1/g, "h2");

			var help = document.getElementById("section_" + path.replace(/\//g, "_"));
			var title = article.getElementsByTagName("h2")[0];
			if(title && title.id) {
				var title_content = title.nextSibling.nextSibling;
				if (Object.keys(sections).length > 1) {
					title.innerHTML = title.innerHTML.replace(/.* - /, "");
					help.appendChild(title);
				}
				help.appendChild(title_content);

				for (var sectionName in sections[path]) {
					var section = document.getElementById(sectionName);
					if (section) {
						var section_content = section.nextSibling.nextSibling;
						help.appendChild(section);
						help.appendChild(section_content);
		
						for (var optionName in sections[path][sectionName]) {
							var option = document.getElementById(optionName);
							if (option) {
								var option_content = option.nextSibling.nextSibling;
								help.appendChild(option);
								help.appendChild(option_content);
							}
						}
					}
				}
			}
			var edit = document.createElement("a");
			edit.className = "edit";
			edit.innerHTML = "$(TEXTDOMAIN="webserver-www" gt 'Edit')";
			edit.href = wikiUrl + path + "?do=edit";
			edit.target = "wiki";
			help.appendChild(edit);
		}, path);
	}
}
</script>

<style>
.edit {
	display: block;
	text-align: right;
	margin-bottom: 10px;
}
#help {
	margin-top: -10px;
}
</style>

<div id="help"></div>
<div id="article" style="display:none"></div>
