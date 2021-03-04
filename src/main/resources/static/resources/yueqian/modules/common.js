;
"use strict";
layui.define(["layer", "jquery","table"], function (exports) {
    var $ = layui.jquery;
    var table = layui.table;
    var obj = {
        checkField: function(obj, field) {
            var data = table.checkStatus(obj.config.id).data;
            if (data.length === 0) {
                return "";
            }
            var ids = "";
            for (var i = 0; i < data.length; i++) {
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
        // 判断一个字符串是否为非空串
        isNotEmpty: function (value) {
            return !this.isEmpty(value);
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
        // 日期格式化 时间戳  -> yyyy-MM-dd HH-mm-ss
        dateFormat: function(date, format) {
            var that = this;
            if (that.isEmpty(date)) return "";
            if (!date) return;
            if (!format) format = "yyyy-MM-dd";
            switch (typeof date) {
                case "string":
                    date = new Date(date.replace(/-/, "/"));
                    break;
                case "number":
                    date = new Date(date);
                    break;
            }
            if (!date instanceof Date) return;
            var dict = {
                "yyyy": date.getFullYear(),
                "M": date.getMonth() + 1,
                "d": date.getDate(),
                "H": date.getHours(),
                "m": date.getMinutes(),
                "s": date.getSeconds(),
                "MM": ("" + (date.getMonth() + 101)).substr(1),
                "dd": ("" + (date.getDate() + 100)).substr(1),
                "HH": ("" + (date.getHours() + 100)).substr(1),
                "mm": ("" + (date.getMinutes() + 100)).substr(1),
                "ss": ("" + (date.getSeconds() + 100)).substr(1)
            };
            return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g,
                function() {
                    return dict[arguments[0]];
                });
        },
        // 数组去重
        uniqueFn: function(array) {
            var result = [];
            var hashObj = {};
            for (var i = 0; i < array.length; i++) {
                if (!hashObj[array[i]]) {
                    hashObj[array[i]] = true;
                    result.push(array[i]);
                }
            }
            return result;
        },
        // 数组中的所有元素放入一个字符串
        join: function(array, separator) {
            if ($.common.isEmpty(array)) {
                return null;
            }
            return array.join(separator);
        },
        // 获取form下所有的字段并转换为json对象
        formToJSON: function(formId) {
            var json = {};
            $.each($("#" + formId).serializeArray(), function(i, field) {
                if(json[field.name]) {
                    json[field.name] += ("," + field.value);
                } else {
                    json[field.name] = field.value;
                }
            });
            return json;
        },
        // 获取obj对象长度
        getLength: function(obj) {
            var count = 0;
            for (var i in obj) {
                if (obj.hasOwnProperty(i)) {
                    count++;
                }
            }
            return count;
        },
        // 判断移动端
        isMobile: function () {
            return navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i);
        },
        // 数字正则表达式，只能为0-9数字
        numValid : function(text){
            var patten = new RegExp(/^[0-9]+$/);
            return patten.test(text);
        },
        // 英文正则表达式，只能为a-z和A-Z字母
        enValid : function(text){
            var patten = new RegExp(/^[a-zA-Z]+$/);
            return patten.test(text);
        },
        // 英文、数字正则表达式，必须包含（字母，数字）
        enNumValid : function(text){
            var patten = new RegExp(/^(?=.*[a-zA-Z]+)(?=.*[0-9]+)[a-zA-Z0-9]+$/);
            return patten.test(text);
        },
        // 英文、数字、特殊字符正则表达式，必须包含（字母，数字，特殊字符!@#$%^&*()-=_+）
        charValid : function(text){
            var patten = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#\$%\^&\*\(\)\-=_\+])[A-Za-z\d~!@#\$%\^&\*\(\)\-=_\+]{6,}$/);
            return patten.test(text);
        },
        //格式化金额
        formatFloat:function(src, pos){
            var num = parseFloat(src).toFixed(pos);
            num = num.toString().replace(/\$|\,/g,'');
            if(isNaN(num)) num = "0";
            var sign = (num == (num = Math.abs(num)));
            num = Math.floor(num*100+0.50000000001);
            var cents = num%100;
            num = Math.floor(num/100).toString();
            if(cents<10) cents = "0" + cents;
            for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
                num = num.substring(0,num.length-(4*i+3))+','+num.substring(num.length-(4*i+3));
            return (((sign)?'':'-') + num + '.' + cents);
        }
    };
    exports('common', obj);
});