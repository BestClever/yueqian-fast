layui.define(['layer', 'jquery', 'element','common'], function(exports) {
	"use strict";

	var MOD_NAME = 'popup',
		$ = layui.jquery,
		layer = layui.layer,
		element = layui.element,
		common = layui.common;

	var popup = new function() {

		this.success = function(msg) {
				layer.msg(msg, {
					icon: 1,
					time: 1000
				})
			},
		this.failure = function(msg) {
			layer.msg(msg, {
				icon: 2,
				time: 1000
			})
		},
		this.warming = function(msg) {
			layer.msg(msg, {
				icon: 3,
				time: 1000
			})
		},
		this.success = function(msg, callback) {
			layer.msg(msg, {
				icon: 1,
				time: 1000
			}, callback);
		},
		this.failure = function(msg, callback) {
			layer.msg(msg, {
				icon: 2,
				time: 1000
			}, callback);
		},
		this.warming = function(msg, callback) {
			layer.msg(msg, {
				icon: 3,
				time: 1000
			}, callback);
		},
		// 弹出层指定参数选项
		this.openOptions= function (options) {
		var _url = common.isEmpty(options.url) ? "/404.html" : options.url;
		var _title = common.isEmpty(options.title) ? "系统窗口" : options.title;
		var _width = common.isEmpty(options.width) ? "800" : options.width;
		var _height = common.isEmpty(options.height) ? ($(window).height() - 50) : options.height;
		var _btn = ['<span class=layui-icon>&#xe605;确认</span>', '<span class=layui-icon>&#x1006;关闭</span>'];
		// 如果是移动端，就使用自适应大小弹窗
		if (common.isMobile()) {
			_width = 'auto';
			_height = 'auto';
		}
		if (common.isEmpty(options.yes)) {
			options.yes = function(index, layero) {
				options.callBack(index, layero);
			}
		}
		var btnCallback = {};
		if(options.btn instanceof Array){
			for (var i = 1, len = options.btn.length; i < len; i++) {
				var btn = options["btn" + (i + 1)];
				if (btn) {
					btnCallback["btn" + (i + 1)] = btn;
				}
			}
		}
		var index = layer.open($.extend({
			type: 2,
			maxmin: common.isEmpty(options.maxmin) ? true : options.maxmin,
			shade: 0.3,
			title: _title,
			fix: false,
			area: [_width + 'px', _height + 'px'],
			content: _url,
			shadeClose: common.isEmpty(options.shadeClose) ? true : options.shadeClose,
			skin: options.skin,
			btn: common.isEmpty(options.btn) ? _btn : options.btn,
			yes: options.yes,
			cancel: function () {
				return true;
			}
		}, btnCallback));
		if (common.isNotEmpty(options.full) && options.full === true) {
			layer.full(index);
		}
	}
	};
	exports(MOD_NAME, popup);
})
