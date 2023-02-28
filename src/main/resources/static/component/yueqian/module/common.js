;
"use strict";
layui.define(["layer", "jquery", "table", "util", "layerCustom"], function (exports) {
    var $ = layui.jquery;
    var util = layui.util;
    var layerCustom = layui.layerCustom;
    var table = layui.table;

    // 自定义form表单json序列化
    $.fn.serializeObject = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    /*
    * ajax 全局配置处理类
    * 1.全局请求拦截
    * 2.全局返回拦截
    *
    *
    $.ajaxSetup({
        complete: function (XMLHttpRequest, textStatus) {
            //通过XMLHttpRequest取得响应结果
            var res = XMLHttpRequest.responseJSON;
            var status = XMLHttpRequest.status;
            if(status!='200'){
                layerCustom.redCryMsg("系统错误！")
            }
            if (obj.isEmpty(res)) {
                layerCustom.redCryMsg("返回的不是一个标准的json")
            }
            if(res.code=='401'){
                layerCustom.yellowSighMsg(res.msg, function () {
                    location.href = "/portal/login";
                });
            }
        }
    });
*/
    /**
     * ajax请求判断会话是否已过期
     */
    $(document).ajaxError(function (event, jqXHR, options, errorMsg) {
        var sessionStatus = jqXHR.getResponseHeader('SessionStatus');
        var ServerType = jqXHR.getResponseHeader('ServerType');
        var toLogin = jqXHR.responseJSON.data;
        if (sessionStatus && sessionStatus === 'sessionTimeOut') {
            layerCustom.yellowSighMsg("您的会话已过期，请重新登录", function () {
                location.href = toLogin;
            });

        }
    });
    var obj = {
        checkField: function (obj, field) {
            let data = table.checkStatus(obj.config.id).data;
            if (data.length === 0) {
                return "";
            }
            let ids = "";
            for (let i = 0; i < data.length; i++) {
                ids += data[i][field] + ",";
            }
            ids = ids.substr(0, ids.length - 1);
            return ids;
        },
        resizeTable: function (tableId) {
            layui.table.resize(tableId);
        }
        , sprintf: function (str) {
            var args = arguments, flag = true, i = 1;
            str = str.replace(/%s/g, function () {
                var arg = args[i++];
                if (typeof arg === 'undefined') {
                    flag = false;
                    return '';
                }
                return arg;
            });
            return flag ? str : '';
        },
        equals: function (str, that) {
            return str == that;
        },
        equalsIgnoreCase: function (str, that) {
            return String(str).toUpperCase() === String(that).toUpperCase();
        },
        isEmpty: function (value) {
            if (typeof (value) === "undefined" || value == null || this.trim(value) == "") {
                return true;
            }
            return false;
        },
        formatNullStr: function (o) {
            if (this.isEmpty(o)) {
                return "";
            } else {
                return o;
            }
        },
        getJsonArrayValue: function (array, key, keyChecked, keyId) {
            var aa = [];
            for (var a in array) {
                var _item = array[a];
                if (_item[keyChecked]) {
                    aa.push(_item[keyId]);
                }
                if (typeof (_item[key]) != "undefined" && _item[key].length > 0) {
                    var _aa = this.getJsonArrayValue(_item[key], key, keyChecked, keyId);
                    if (_aa != null && _aa.length > 0) {
                        for (var _a in _aa) {
                            aa.push(_aa[_a]);
                        }
                    }
                }
            }
            return aa;
        },
        trim: function (value) {
            if (value == null) {
                return "";
            }
            return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
        },
        random: function (min, max) {
            return Math.floor((Math.random() * max) + min);
        },
        //uuid 生成器
        uuid: function(){
            var s = [];
            var hexDigits = "0123456789abcdef";
            for (var i = 0; i < 32; i++) {
                s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
            }
            s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
            s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
            s[8] = s[13] = s[18] = s[23];
            var uuid = s.join("");
            return uuid;
        },
        getCheckValues: function (name) {
            var _items = $('input:checkbox[name*="' + name + '"]:checked');
            var _itemsStr = "";
            layui.each(_items, function (i, n) {
                _itemsStr += "," + $(n).val();
            });
            if (_itemsStr.length > 0) {
                return _itemsStr.substr(1, _itemsStr.length);
            }
            return "";
        },
        joinArray: function (array, key) {
            var _itemsStr = "";
            layui.each(array, function (i, n) {
                _itemsStr += "," + n[key];
            });
            if (_itemsStr.length > 0) {
                return _itemsStr.substr(1, _itemsStr.length);
            }
            return "";
        },
        getDictLabel: function (array, value) {
            var actions = [];
            layui.each(array, function (i, n) {
                if (n.dictValue === value) {
                    actions.push(n.dictLabel);
                }
            });
            return actions.join('');
        },
        ajaxRemove: function (removeUrl, id, cb) {
            if (id == '' || id == undefined) {
                layerCustom.yellowSighMsg('请选择删除数据！');
                return;
            }
            var url = this.isEmpty(id) ? removeUrl : removeUrl.replace("{id}", id);
            var msg = (id.length > 0 && id.indexOf(",") > 0) ? "是否确认删除这些项？" : "是否确认删除该项？";
            layerCustom.confirm(msg, function (index) {
                $.ajax({
                    type: "POST",
                    url: url,
                    async: true,
                    cache: false,
                    dataType: "json",
                    data: {"id": id},
                    success: function (res) {
                        if (typeof (cb) === "function") {
                            cb(res);
                        }
                        layer.close(index);
                    }
                });
            });
        },
        ajax: {
            submit: function (url, type, dataType, data, cb) {
                var config = {
                    url: url,
                    type: type,
                    dataType: dataType,
                    data: data,
                    beforeSend: function () {
                        layer.load(2);
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if (typeof (cb) === "function") {
                            cb(result);
                        }
                    },
                    error: function (e) {
                        layer.closeAll('loading');
                        layerCustom.redCryMsg('程序出错了');
                    }
                };
                $.ajax(config)
            },
            submitWithJson: function (url, type, dataType, data, cb) {
                var config = {
                    url: url,
                    type: type,
                    dataType: dataType,
                    data: data,
                    contentType: 'application/json',
                    beforeSend: function () {
                        layer.load(2);
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if (typeof (cb) === "function") {
                            cb(result);
                        }
                    },
                    error: function (e) {
                        layer.closeAll('loading');
                        layerCustom.redCryMsg('程序出错了');
                    }
                };
                $.ajax(config)
            },
            post: function (url, data, cb) {
                obj.ajax.submit(url, "post", "json", data, cb);
            },
            // 后台 使用 @RequestBody 接收
            postWithJson: function (url, data, cb) {
                obj.ajax.submitWithJson(url, "post", "json", data, cb);
            },
            get: function (url, cb) {
                obj.ajax.submit(url, "get", "json", "", cb);
            }
        },
        /*===============================自己写的方法==========================*/
        previewImg: function (path, height, width) {
            //页面层-图片
            var img = new Image();
            img.src = path;
            var heightP = img.height + 60; //获取图片高度
            if (!this.isEmpty(height)) {
                var heightP = height;
            }
            var widthP = img.width + 20; //获取图片宽度
            if (!this.isEmpty(width)) {
                var widthP = width;
            }
            var imgHtml = "<div style='height:95%;display:flex;justify-content: center;align-items: center;'><img src='" + path + "' /></div>";
            //弹出层
            layer.open({
                type: 1,
                shade: 0.8,
                offset: 'auto',
                area: [widthP + 'px', heightP + 'px'],
                shadeClose: true,//点击外围关闭弹窗
                scrollbar: false,//不现实滚动条
                title: false, //不显示标题
                closeBtn: 0,
                content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function () {
                    //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
                }
            });
        }
    };
    //固定Bar
    util.fixbar({
        showHeight: 60
        , bgcolor: '#9F9F9F'
    });
    exports('common', obj);
});