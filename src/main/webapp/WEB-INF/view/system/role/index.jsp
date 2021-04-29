<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>角色表列表</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="" placeholder="" class="layui-input">
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
        <div class="layui-btn-group operateTable" style="margin: 5px">
            <button class="yueqian-btn yueqian-btn-primary yueqian-btn-md" data-type="add">
                <i class="layui-icon layui-icon-add-1"></i>
                新增
            </button>
        </div>
        <table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>

<!--操作-->
<script type="text/html" id="tableTool">
    <a class="yueqian-btn yueqian-btn-primary yueqian-btn-md" lay-event="authorization">
        <i class="iconfont layui-icon-ali-shouquan"></i>
        授权
    </a>
    <a class="yueqian-btn yueqian-btn-warming yueqian-btn-md" lay-event="update">
        <i class="iconfont layui-icon-ali-xiugai"></i>
        修改
    </a>
    <a class="yueqian-btn yueqian-btn-danger yueqian-btn-md" lay-event="del">
        <i class="iconfont layui-icon-ali-shanchu1"></i>
        删除
    </a>
</script>


<script type="text/html" id="isAvailableTps">
    {{# if(d.isAvailable=='0'){ }}
    <span class="layui-bg-green yueqian-span">正常</span>
    {{# }else if(d.isAvailable=='1'){ }}
    <span class="layui-bg-orange yueqian-span">停用</span>
    {{# } }}
</script>

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'layerCustom'], function () {
        var element = layui.element;
        table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            form = layui.form;

        var myTalbe = table.render({
            elem: '#tableId'
            , url: BaseUrl + 'sysRole/list'
            , skin: 'line'
            , cols: [[
                {type: 'checkbox'},
                {field: 'roleName', title: '角色名称'},
                {field: 'roleCode', title: '角色编码'},
                {field: 'roleDescription', title: '角色描述'},
                {field: 'isAvailable', title: '是否可用',templet: '#isAvailableTps'},
                {field: 'remark', title: '备注'},
                {title: '操作', templet: '#tableTool', width: 250, align: 'center'}
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
                case "authorization":
                    authorization(data);
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

        function add() {
            layerCustom.open("添加用户", BaseUrl + 'sysRole/add', "700px", "550px", function (layero, index) {
            })
        }

        function update(data) {
            layerCustom.open("修改用户信息", BaseUrl + 'sysRole/edit', "700px", "450px", function (layero, index) {
                //给子页面赋值
                var iframeWin = window[layero.find("iframe")[0]["name"]];
                iframeWin.initForm(data);
            })
        }

        function del(data) {
            layerCustom.confirm("是否要删除该条数据", function () {
                var loading = layer.load();
                var params = {
                    id: data.id
                }
                $.ajax({
                    url: BaseUrl + "sysRole/remove",
                    data: params,
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layerCustom.greenLaughMsg(result.msg, function () {
                                myTalbe.reload();
                            });
                        } else {
                            layerCustom.redCryMsg(result.msg);
                        }
                    }
                })
            })
        }

        function authorization(data) {
            layerCustom.open("权限选择", BaseUrl + 'sysRole/authorization?id='+data.id, "400px", "80%", function (layero, index) {
            });
        }
    });
</script>
</body>
</html>


