window.rootPath = (function(src) {
	src = document.scripts[document.scripts.length - 1].src;
	return src.substring(0, src.lastIndexOf("/") + 1);
})();

layui.config({
	base: rootPath + "module/",
	version: "3.8.0"
}).extend({
	admin: "admin",
	menu: "menu",
	frame: "frame",
	tab: "tab",
	echarts: "echarts",
	echartsTheme: "echartsTheme",
	hash: "hash",
	document: "document",
	select: "select",
	drawer: "drawer",
	notice: "notice",
	step:"step",
	tag:"tag",
	popup:"popup",
	iconPicker:"iconPicker",
	treetable:"treetable",
	dtree:"dtree",
	tinymce:"tinymce/tinymce", // 编辑器
	area:"area",
	count:"count",
	topBar: "topBar",
	button: "button",
	design: "design",
	common: "common",
	eleTree: "eleTree",
	dictionary: 'dictionary',
	layerCustom:"layerCustom",
	json: 'json',
	cropper:"cropper",
	yaml: "yaml",
	theme: "theme",
	bodyTab: "bodyTab",
	message: "message"		// 通知组件
});
