"use strict";
layui.define(["layer"], function (exports) {
    var layerCustom = {
        /**
         * confirm()函数二次封装
         * @param content
         * @param yesFunction
         */
        confirm: function (content, yesFunction) {
            var options = {skin: layerCustom.skinChoose(), icon: 3, title: "提示", anim: layerCustom.animChoose()};
            layer.confirm(content, options, yesFunction);
        },
        /**
         * open()函数二次封装,支持在table页面和普通页面打开
         * @param title
         * @param content
         * @param width
         * @param height
         * @param successFunction
         * @param endFunction
         */
        open: function (title, content, width, height, successFunction, endFunction) {
           var index = layer.open({
                title: title,
                type: 2,
                maxmin: true,
                shade: 0.5,
                // anim: layerCustom.animChoose(),
                area: [width, height],
                content: content,
                zIndex: layer.zIndex,
                skin: layerCustom.skinChoose(),
                success: successFunction,
                end: endFunction
            });
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            // $(window).on("resize",function(){
            //     layui.layer.full(index);
            // })
        },
        /**
         * 弹出及全屏
         * @param title
         * @param content
         * @param width
         * @param height
         * @param successFunction
         * @param endFunction
         */
        openFull:function(title, content, width, height, successFunction, endFunction){
            var index = layer.open({
                title: title,
                type: 2,
                maxmin: true,
                shade: 0.5,
                // anim: layerCustom.animChoose(),
                area: [width, height],
                content: content,
                zIndex: layer.zIndex,
                skin: layerCustom.skinChoose(),
                success: successFunction,
                end: endFunction
            });
            layer.full(index);
        },
        /**
         * msg()函数二次封装
         */
        // msg弹窗默认消失时间
        time: 1000,
        // 绿色勾
        greenTickMsg: function (content, callbackFunction) {
            var options = {icon: 1, time: layerCustom.time, anim: layerCustom.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 红色叉
        redCrossMsg: function (content, callbackFunction) {
            var options = {icon: 2, time: layerCustom.time, anim: layerCustom.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 黄色问号
        yellowQuestionMsg: function (content, callbackFunction) {
            var options = {icon: 3, time: layerCustom.time, anim: layerCustom.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 灰色锁
        grayLockMsg: function (content, callbackFunction) {
            var options = {icon: 4, time: layerCustom.time, anim: layerCustom.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 红色哭脸
        redCryMsg: function (content, callbackFunction) {
            var options = {icon: 5, time: layerCustom.time, anim: layerCustom.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 绿色笑脸
        greenLaughMsg: function (content, callbackFunction) {
            var options = {icon: 6, time: layerCustom.time, anim: layerCustom.animChoose()};
            layer.msg(content, options, callbackFunction);
        },
        // 黄色感叹号
        yellowSighMsg: function (content, callbackFunction) {
            var options = {icon: 7, time: layerCustom.time, anim: layerCustom.animChoose()};
            layer.msg(content, options, callbackFunction);
        },

        /**
         * 在表格页面操作成功后弹窗提示
         * @param content
         */
        tableSuccessMsg: function (content) {
            layer.msg(content, {icon: 1, time: 1000}, function () {
                // 刷新当前页table数据
                $(".layui-laypage-btn")[0].click();
            });
        },
        /**
         * 皮肤选择
         * @returns {string}
         */
        skinChoose: function () {
            var storage = window.localStorage;
            var skin = 1;
            if (skin == 1) {
                // 灰白色
                return "";
            } else if (skin == 2) {
                // 墨绿色
                return "layui-layer-molv";
            } else if (skin == 3) {
                // 蓝色
                return "layui-layer-lan";
            } else if (!skin || skin == 4) {
                // 随机颜色
                var skinArray = ["", "layui-layer-molv", "layui-layer-lan"];
                return skinArray[Math.floor(Math.random() * skinArray.length)];
            }
        },
        /**
         * 动画选择
         * @returns {number}
         */
        animChoose: function () {
            var storage = window.localStorage;
            var anim = "6";
            var animArray = ["0", "1", "2", "3", "4", "5", "6"];
            if (animArray.indexOf(anim) > -1) {
                // 用户选择的动画
                return anim;
            } else if (!anim || anim == 7) {
                // 随机动画
                return Math.floor(Math.random() * animArray.length);
            }
        }
    }

    exports("layerCustom", layerCustom);
});
