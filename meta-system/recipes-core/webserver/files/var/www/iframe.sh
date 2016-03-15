<? 
. functions.sh

session_start 
?>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title>$HOST_NAME@Home</title>

	<link href="/images/favicon.ico" rel="shortcut icon">
	<link href="/images/favicon.ico" rel="icon" type="image/ico">

	<link href="/iframe.css" rel="stylesheet" type="text/css">

	<script>
		window.onload = function() {
			if(self==top) {
				document.body.className='window'
			} else {
				var dest = top.document.getElementsByTagName("iframe")[window.name].parentNode.parentNode;
				var elements = document.getElementsByClassName("icon");
				for (var i=elements.length-1; i>=0; i--) {
					dest.insertBefore(elements[i], dest.firstChild);
				}
			}
		};
	</script>
	
</head>
<body id="body" class="iframe">

<div class="text">
	<? include tpl/${GET_site-index}.sh ?>
</div>

</body>
</html>
