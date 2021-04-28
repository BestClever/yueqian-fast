layui.define(["jquery","layer"], function (exports) {
	var MOD_NAME = 'theme',
	    $ = layui.jquery;

	var theme = {};
	theme.autoHead = false;

	theme.changeTheme = function (target, autoHead) {
		this.autoHead = autoHead;
		var color = localStorage.getItem("theme-color-context");
		this.colorSet(color);
		if (target.frames.length == 0) return;
		for (var i = 0; i < target.frames.length; i++) {
			try {
				if(target.frames[i].layui == undefined) continue;
				target.frames[i].layui.theme.changeTheme(target.frames[i], autoHead);
			}
			catch (error) {
				console.log(error);
			}
		}
	}

	theme.colorSet = function(color) {
		
		var style = '';
		style += '.light-theme .yueqian-nav-tree .layui-this a:hover,.light-theme .yueqian-nav-tree .layui-this,.light-theme .yueqian-nav-tree .layui-this a,.yueqian-nav-tree .layui-this a,.yueqian-nav-tree .layui-this{background-color: ' +color + '!important;}';
		style += '.yueqian-admin .layui-logo .title{color:' + color + '!important;}';
		style += '.yueqian-frame-title .dot,.yueqian-tab .layui-this .yueqian-tab-active{background-color: ' + color +'!important;}';
		style += '.bottom-nav li a:hover{background-color:' + color + '!important;}';
		style += '.yueqian-admin .layui-header .layui-nav .layui-nav-bar{background-color: ' + color + '!important;}'
		style += '.ball-loader>span,.signal-loader>span {background-color: ' + color + '!important;}';
		style += '.layui-header .layui-nav-child .layui-this a{background-color:' + color +'!important;color:white!important;}';
		style += '#preloader{background-color:' + color + '!important;}';
		style += '.yueqianone-color .color-content li.layui-this:after, .yueqianone-color .color-content li:hover:after {border: ' +color + ' 3px solid!important;}';
		style += '.layui-nav .layui-nav-child dd.layui-this a, .layui-nav-child dd.layui-this{background-color:' + color + '!important}';	
		style += '.yueqian-social-entrance {background-color:' + color + '!important}';
		style += '.yueqian-admin .pe-collaspe {background-color:' + color + '!important}';
		style += '.layui-fixbar li {background-color:' + color + '!important}';
		if(this.autoHead){
			style += '.yueqian-admin .layui-header{background-color:' + color + '!important;}.yueqian-admin .layui-header .layui-nav .layui-nav-item>a{color:white!important;}';
		}
		style += '.yueqian-btn-primary {background-color:' + color + '!important}';
		style += '.layui-input:focus,.layui-textarea:focus {border-color: '+ color +'!important;}'
		style += '.layui-form-checked[lay-skin=primary] i {border-color: '+ color +'!important;background-color: ' + color + ';}'
		style += '.layui-form-onswitch { border-color: ' + color + '; background-color: '+color+';}'
		style += '.layui-form-radio>i:hover, .layui-form-radioed>i {color: ' + color + ';}'
		style += '.layui-laypage .layui-laypage-curr .layui-laypage-em{background-color:'+ color +'!important}'
		style += '.layui-tab-brief>.layui-tab-more li.layui-this:after, .layui-tab-brief>.layui-tab-title .layui-this:after{border-bottom: 2px solid '+color+'!important}'
		style += '.layui-tab-brief>.layui-tab-title .layui-this{color:'+color+'!important}'
		style += '.layui-progress-bar{background-color:'+color+'}';
		style += '.layui-elem-quote{border-left: 5px solid '+ color +'}';
		style += '.layui-timeline-axis{color:' + color + '}';
		style += '.layui-laydate .layui-this{background-color:'+color+'!important}';
		style += '.yueqian-text{color:' + color + '!important}';
		style += '.yueqian-collasped-pe{background-color:'+color+'!important}'
		style += '.layui-form-select dl dd.layui-this{background-color:'+color+'}'
		style += '.tag-item-normal{background:'+color+'!important}';
		style += '.step-item-head.step-item-head-active{background-color:'+color+'}'
		style += '.step-item-head{border: 3px solid '+color+';}'
		style += '.step-item-tail i{background-color:'+color+'}'
		style += '.step-item-head{color:' + color + '}'
		style += 'div[xm-select-skin=normal] .xm-select-title div.xm-select-label>span i {background-color:'+color+'!important}'
		style += 'div[xm-select-skin=normal] .xm-select-title div.xm-select-label>span{border: 1px solid '+color+'!important;background-color:'+color+'!important}'
		style += 'div[xm-select-skin=normal] dl dd:not(.xm-dis-disabled) i{border-color:'+color+'!important}'
		style += 'div[xm-select-skin=normal] dl dd.xm-select-this:not(.xm-dis-disabled) i{color:'+color+'!important}'
		style += 'div[xm-select-skin=normal].xm-form-selected .xm-select, div[xm-select-skin=normal].xm-form-selected .xm-select:hover{border-color:'+color+'!important}'
		style += '.user-group.button-primary{background-color:'+color+'!important}'
		style += '.layui-layer-btn a:first-child{border-color:'+color+';background-color:'+color+'!important}'
		style += '.layui-form-checkbox[lay-skin=primary]:hover i{border-color:'+color+'!important}'
		style += '.yueqian-tab-menu .item:hover{background-color:'+color+'!important}'

		var colorPane = $("#yueqian-admin-color");
		if(colorPane.length>0){
			colorPane.html(style);
		}else{
			$("head").append("<style id='yueqian-admin-color'>"+style+"</style>")
		}
	}

	exports(MOD_NAME, theme);
});