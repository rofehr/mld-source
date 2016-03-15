<style>
#body .text pre {
	margin: 0;
	padding: 0;
	background: none;
	border: 0;
	box-shadow: unset;
	overflow: visible;
	white-space: pre-wrap;
}
</style>

<h1>$GET_name</h1>

<? if [ -n "$GET_file" ]; then ?>
	<pre><? cat $GET_file ?></pre>
<? fi ?>
