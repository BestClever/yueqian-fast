<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>班级列表</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">班级名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="classeName" placeholder="" class="layui-input">
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

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'common', 'layerCustom'], function () {
        var element = layui.element;
        table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            form = layui.form;
        common = layui.common;

        var myTalbe = table.render({
            elem: '#tableId'
            , url: BaseUrl + 'classe/list'
            , skin: 'line'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'classeName', title: '班级名称', width: 150}
                , {field: 'classeSize', title: '班级人数', width: 100}
                , {field: 'classeNo', title: '班级编号', width: 100}
                , {field: 'classeGrade', title: '年级', width: 100}
                , {field: 'classeTeacheName', title: '班主任名称', width: 100}
                , {field: 'assistantName', title: '辅导员名称', width: 100}
            ]]
            , page: true
            ,where:{
                collegeId:'${collegeId}'
            }
        });

        form.on("submit(search)", function (data) {
            myTalbe.reload({
                where: data.field,
                page: {curr: 1}
            });
            return false;
        });
    });
</script>
</body>
</html>

