<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>部门列表</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="userName" placeholder="" class="layui-input">
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
        <table id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>


<!--操作-->
<script type="text/html" id="tableTool">
    {{# if(d.parentId !='0'){ }}
    <a class="yueqian-btn yueqian-btn-primary yueqian-btn-md" lay-event="add">
        <i class="iconfont layui-icon-ali-add"></i>
        新增
    </a>
    <a class="yueqian-btn yueqian-btn-warming yueqian-btn-md" lay-event="update">
        <i class="iconfont layui-icon-ali-xiugai"></i>
        修改
    </a>
    <a class="yueqian-btn yueqian-btn-danger yueqian-btn-md" lay-event="del">
        <i class="iconfont layui-icon-ali-shanchu1"></i>
        删除
    </a>
    {{# } }}

</script>

<script type="text/html" id="toolbar">
    <button class="yueqian-btn yueqian-btn-primary yueqian-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
    <button class="yueqian-btn yueqian-btn-success yueqian-btn-md" lay-event="expandAll">
        <i class="layui-icon layui-icon-spread-left"></i>
        展开
    </button>
    <button class="yueqian-btn yueqian-btn-success yueqian-btn-md" lay-event="foldAll">
        <i class="layui-icon layui-icon-shrink-right"></i>
        折叠
    </button>
</script>

<%----%>
<script type="text/html" id="isAvailableTps">
    <%--<input type="checkbox" name="isAvailable" value="{{d.isAvailable}}" lay-skin="switch" lay-text="启用|禁用" lay-filter="switchFilter" {{ d.isAvailable == 0 ? 'checked' : '' }}>--%>
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
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'layerCustom', 'treetable'], function () {
        var element = layui.element;
        $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            table = layui.table,
            form = layui.form;
        treetable = layui.treetable;

        window.renderTreeTalbe = function(){
            treetable.render({
                treeColIndex: 1,
                treeSpid: 0,
                treeIdName: 'id',
                treePidName: 'parentId',
                skin: 'line',
                method: 'get',
                treeDefaultClose: false,
                toolbar: '#toolbar',
                elem: '#tableId',
                url: BaseUrl + "sysDept/list",
                page: false,
                cols: [
                    [
                        {type: 'checkbox'},
                        {field: 'deptName', minWidth: 200, title: '部门名称'},
                        {field: 'sortNum', title: '排序'},
                        {field: 'isAvailable', title: '是否有效', templet: '#isAvailableTps'},
                        {field: 'createTime', title: '创建时间'},
                        {title: '操作', templet: '#tableTool', width: 250, align: 'center'}
                    ]
                ]
            });
        };

        renderTreeTalbe();

        table.on("tool(tableFilter)", function (obj) {
            var data = obj.data;
            switch (obj.event) {
                case "update":
                    update(data);
                    break;
                case "del":
                    del(data);
                    break;
                case "add":
                    addSingle(data);
                    break;
            }
        });

        /*监听工具栏*/
        table.on('toolbar(tableFilter)', function (obj) {
            switch (obj.event) {
                case "add":
                    add();
                    break;
                case "batchRemove":
                    batchRemove();
                    break;
                case "expandAll":
                    treetable.expandAll("#tableId");
                    break;
                case "foldAll":
                    treetable.foldAll("#tableId");
                    break;
            }
        });

        form.on("submit(search)", function (data) {
            myTalbe.reload({
                where: data.field,
                page: {curr: 1}
            });
            return false;
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
            layerCustom.open("添加部门", BaseUrl + 'sysDept/add', "700px", "550px", function (layero, index) {
            })
        }

        function addSingle(data) {
            layerCustom.open("添加部门", BaseUrl + 'sysDept/add', "700px", "550px", function (layero, index) {
                var body = layer.getChildFrame('body', index);
                body.find("#parentName").val(data.deptName);
                body.find("#parentId").val(data.id);
            });
        }

        function update(data) {
            layerCustom.open("修改部门信息", BaseUrl + 'sysDept/edit', "700px", "450px", function (layero, index) {
                //给子页面赋值
                var iframeWin = window[layero.find("iframe")[0]["name"]];
                iframeWin.initForm(data);
            })
        }

        function del(data) {
            layerCustom.confirm("是否要删除该条数据？", function () {
                var loading = layer.load();
                var params = {
                    id: data.id
                }
                $.ajax({
                    url: BaseUrl + "sysDept/remove",
                    data: params,
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layerCustom.greenLaughMsg(result.msg, function () {
                                renderTreeTalbe();
                            });
                        } else {
                            layerCustom.redCryMsg(result.msg);
                        }
                    }
                })
            })
        }
    });
</script>
</body>
</html>
