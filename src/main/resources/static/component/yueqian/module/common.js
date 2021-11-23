;
"use strict";
layui.define(["layer", "jquery","table","util"], function (exports) {
    var $ = layui.jquery;
    var util = layui.util;
    var table = layui.table;
    var obj = {
        checkField: function(obj, field) {
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
        resizeTable:function(tableId){
            layui.table.resize(tableId);
        }
        ,sprintf: function (str) {
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
            if(id=='' || id==undefined){
                layui.layer.alert('请选择删除数据！');
                return;
            }
            var url = this.isEmpty(id) ? removeUrl : removeUrl.replace("{id}", id);
            var msg = (id.length > 0 && id.indexOf(",") > 0) ? "是否确认删除这些项？" : "是否确认删除该项？";
            layer.confirm(msg, function (index) {
                $.ajax({
                    type: "POST",
                    url: url,
                    async: true,
                    cache: false,
                    dataType: "json",
                    data: {"ids": id},
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
                        //layer.loading("正在处理中，请稍后...");
                        layer.load(2);
                    },
                    success: function (result) {
                        layer.closeAll('loading');
                        if (typeof (cb) === "function") {
                            cb(result);
                        }
                    }
                };
                $.ajax(config)
            },
            post: function (url, data, cb) {
                obj.ajax.submit(url, "post", "json", data, cb);
            },
            get: function (url, cb) {
                obj.ajax.submit(url, "get", "json", "", cb);
            }
        },
        verify: {
            roleKey: function (value, item) {
                var msg;
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    msg = 'roleKey不能有特殊字符';
                }
                $.ajax({
                    type: "POST",
                    url: "/system/role/checkRoleKeyUnique",
                    async: false,
                    cache: false,
                    dataType: "json",
                    data: {
                        roleKey: $("[name='roleKey']").val()
                    },
                    success: function (res) {
                        if (res != "0") {
                            msg = "roleKey已存在，请修改！";
                        }
                    },
                    error: function () {
                        msg = "验证roleKey出错！";
                    }
                });
                return msg;
            },
            roleName: function (value, item) {
                var msg;
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    msg = 'roleName不能有特殊字符';
                }
                $.ajax({
                    type: "POST",
                    url: "/system/role/checkRoleNameUnique",
                    async: false,
                    cache: false,
                    dataType: "json",
                    data: {
                        roleName: $("[name='roleName']").val()
                    },
                    success: function (res) {
                        if (res != "0") {
                            msg = "roleName已存在，请修改！";
                        }
                    },
                    error: function () {
                        msg = "验证roleName出错！";
                    }
                });
                return msg;
            }
        },
        /*===============================自己写的方法==========================*/
        previewImg: function (path,height,width) {
            //页面层-图片
            var img = new Image();
            img.src = path;
            var heightP = img.height + 60; //获取图片高度
            if(!this.isEmpty(height)){
                var heightP = height;
            }
            var widthP = img.width + 20; //获取图片宽度
            if(!this.isEmpty(width)){
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
        ,bgcolor: '#9F9F9F'
    });
    exports('common', obj);
});