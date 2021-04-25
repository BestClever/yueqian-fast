<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考核评估</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">考核内容</label>
                <div class="layui-input-inline">
                    <input type="text" name="assessContent" placeholder="" class="layui-input">
                </div>
                <label class="layui-form-label">题目类型</label>
                <div class="layui-input-inline">
                    <select name="assessType">
                        <option value="">请选择题目类型</option>
                        <option value="1">老师</option>
                        <option value="2">辅导员</option>
                        <option value="3">学生</option>
                    </select>
                </div>
                <button class="yueqian-btn yueqian-btn-md yueqian-btn-primary" lay-submit lay-filter="search">
                    <i class="layui-icon layui-icon-search"></i>
                    查询
                </button>
                <button type="reset" class="yueqian-btn yueqian-btn-md">
                    <i class="layui-icon layui-icon-refresh"></i>
                    重置
                </button>
            </div>
        </form>
    </div>
</div>

<div class="layui-card">
    <div class="layui-card-body">
        <table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>

<%--题目类型--%>
<script type="text/html" id="assessTypeTpl">
    {{#if (d.assessType == 1) { }}
    <span class="layui-blue">老师</span>
    {{# }else if(d.assessType == 2){ }}
    <span class="layui-green">辅导员</span>
    {{# }else if(d.assessType == 3){ }}
    <span class="layui-orange">学生</span>
    {{# } }}
</script>
<!--操作-->
<script type="text/html" id="tableTool">
    <a class="layui-btn layui-btn-xs layui-bg-red"  lay-event="del">删除</a>
</script>

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'layerCustom','common'], function () {
        var element = layui.element;
        table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            form = layui.form;
            common = layui.common;

        var myTalbe = table.render({
            elem: '#tableId'
            , url: BaseUrl + 'assess/list'
            , skin: 'line'
            , cols: [[
                {field: 'assessContent', title: '考核内容'}
                ,{field: 'batchName', title: '批次名称'}
                , {field: 'assessType', title: '题目类型',templet:"#assessTypeTpl"}
                , {title: '操作', toolbar: '#tableTool', fixed: "right", width: 250}
            ]]
            , page: true

        });

        form.on("submit(search)", function (data) {
            myTalbe.reload({
                where: data.field,
                page: {curr: 1}
            });
            return false;
        });

        table.on("tool(tableFilter)", function (obj) {
            var data = obj.data;
            switch (obj.event) {
                case "update":
                    update(data);
                    break;
                case "del":
                    del(data);
                break;
                case "generateAssess":
                    generateAssess(data);
                break;
            }
        });

        $('.operateTable .yueqian-btn').on('click', function () {
            var type = $(this).data('type');
            switch (type) {
                case "add":
                    add();
                    break;
            }
        });

        function del(data){
            layerCustom.confirm("是否要删除？",function () {
               var loading = layer.load();
                var params ={
                    id:data.id
                }
                $.ajax({
                    url: BaseUrl + "assess/remove",
                    data:params,
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layerCustom.greenLaughMsg(result.msg,function () {
                                myTalbe.reload();
                            });
                        } else {
                            layerCustom.redCryMsg(result.msg);
                        }
                    }
                })
            })
        }

        /**
         * 生成题目
         * @param data
         */
        function generateAssess(data) {
            layerCustom.open("生成评估信息", BaseUrl + 'assessLibrary/generateAssess', "600px", function (layero, index) {
            })
        }


    });
</script>
</body>
</html>

