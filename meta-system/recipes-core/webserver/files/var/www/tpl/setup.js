function store (field)
{
	document.forms['setup']['changed'].value += document.forms['setup']['changed'].value.match(new RegExp(field.name)) ? "" : (field.name + " ");
}

function call (command, params)
{
	document.forms['setup']['action'].value = command;
	document.forms['setup']['params'].value = params;
	document.forms['setup'].submit();
}

function setValue (field, add)
{
	value = document.forms['setup'][field.name].options[document.forms['setup'][field.name].selectedIndex].value;
	field.previousSibling.value = value=="-" ? "" : ((add && value!="auto" && field.previousSibling.value && field.previousSibling.value!="auto" ? (field.previousSibling.value+",") : "") + value);
	if (add) document.forms['setup'][field.name].selectedIndex = -1;
	store (field.previousSibling)
}

function selectFile (name)
{
	document.forms['setup'].enctype = "multipart/form-data";
	document.forms['setup'][name].onchange = function () {
		if (document.forms['setup'][this.name+'Input']) {
			document.forms['setup'][this.name+'Input'].value = this.value.replace(/.*\\/, "");
			if (document.forms['setup'][this.name+'Input'].onchange) {
				document.forms['setup'][this.name+'Input'].onchange();
			}
		} else {
			document.forms['setup'].submit();
		}
	};
	document.forms['setup'][name].click();
}

function selectAndInput (name)
{
	var select = document.forms['setup'][name];
	var input = document.forms['setup'][name.replace(/select_/, '')];
	var width = input.offsetWidth;
	select.style.position = "absolute";
	select.style.marginLeft = "24px";
	select.style.width = width+"px";
	select.style.clip = "rect(0,"+width+"px, 30px, "+(width-15)+"px)";
}

function initSelects ()
{
	var form = document.forms['setup'];
	for (var name in form) {
		if (form[name] && form[name].type=="select-one" && form[name].getAttribute ("value")) {
			var options = form[name].options;
			for (var i=0; i<options.length; i++) {
				if (options[i] && options[i].value == decodeURI(form[name].getAttribute ("value"))) {
					options[i].selected = true;
					break;
				}
			}
		}
	}
}

function toId(name)
{
	return name.replace(/ /g, "_").toLowerCase().replace(/ä/g, "ae").replace(/ö/g, "oe").replace(/ü/g, "ue").replace(/ß/g, "ss").replace(/[^0-9a-z_\/-]/g, "");
}

function initHelp (prefix)
{
	var path = "", sections = {};
	for (var element = document.forms['setup'].firstChild; element; element = element.nextSibling) {
		if (element.nodeName == "H2") {
			path = (prefix ? toId(prefix) + "/" : "") + toId(element.textContent.replace(/ *– */g, "/"));
			sections[path] = {}
		}
		if (element.nodeName == "DIV") {
			for (var label = element.firstChild; label; label = label.nextSibling) {
				if (label.nodeName == "LABEL") {
					var section = toId(label.textContent);
					if (section) {
						sections[path][section] = {};
						var options = element.firstChild.nextSibling.options;
						if (options) {
							for (var i=0; i<options.length; i++) {
								sections[path][section][toId(options[i].value)] = {};
							}
						}
					}
				}
				if (label.nodeName == "INPUT") {
					var section = toId(label.value);
					if (section) {
						sections[path][section] = {};
					}
				}
			}
		}
		if (element.nodeName == "UL") {
			for (var list = element.firstChild; list; list = list.nextSibling) {
				if (list.nodeName == "LI") {
					var section = toId(list.textContent);
					if (section) {
						sections[path][section] = {};
					}
				}
			}
		}
	}
	top.showHelp && top.showHelp(sections);
}
