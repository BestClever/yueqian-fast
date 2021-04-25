<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>我的评价记录</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label layui-required">评价阶段</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="batchName" id="batchName">
                    <select lay-verify="required" name="batchNo" lay-filter="LAY-Select" id="batchNameSelect"></select>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="layui-card layui-hide">
    <div class="layui-card-body">
        <div style=" display: flex;flex-direction: column; justify-content: center;align-items: center">
            <div><h1 style="letter-spacing:3px;margin-bottom: 10px">班主任工作测评表（班级学生测评）（满分100分）</h1></div>
        </div>
        <table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>


<div class="layui-card layui-hide">
    <div class="layui-card-body">
        <form class="layui-form" action="" id="LAY_Form">
            <div class="layui-form-item layui-form-text">
                <input type="hidden" name="classeId" id="classeId">
                <label class="layui-form-label layui-required">班级名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="classeName" id="classeName" autocomplete="off" placeholder="请输入班主任名称" class="layui-input" readonly>
                </div>
                <label class="layui-form-label layui-required" style="padding-left: 25px;">班主任名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="classeTeacherName" id="classeTeacherName" autocomplete="off" placeholder="请输入班主任名称" class="layui-input" readonly >
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label layui-required">评价内容</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入评价内容" name="evaluateContent" id="evaluateContent" class="layui-textarea" lay-verify="required"></textarea>
                </div>
            </div>
        </form>
    </div>
</div>


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

        /*代码立即执行*/
        (function () {
            var url =BaseUrl+"assessBatch/list";
            var param ={
                limit:9999,
                page:0
            }
            $.ajax({
                url: url,
                type: "get",
                async: false,
                data: param,
                dataType: "json",
                success: function (result) {
                    // $("#batchNameSelect").append("<option  value='0'>请选择批次</option>");
                    for (var i=0;i < result.data.length;i++) {
                        var item = result.data[i];
                        $("#batchNameSelect").append("<option  value=" + item.id + ">" + item.batchName + "</option>");
                    }
                }
            });
            form.render('select');
        })();

        form.on('select(LAY-Select)', function(data){
            // $("#batchNameSelect").val(data.value)
            var text = data.elem[data.elem.selectedIndex].text;
            $("#batchName").val(text);

            //移除 layui-hide
            $(".layui-card").removeClass("layui-hide");

            var myTalbe = table.render({
                elem: '#tableId'
                , url: BaseUrl + 'assessResulitem/myList'
                , skin: 'line'
                ,totalRow: true
                , cols: [[
                    {type:'numbers', totalRowText: '合计'}
                    ,{field: 'assessContent', title: '考  核  内  容（每一项满分10分）', width:900}
                    , {field: 'assessScore', title: '得分', width:200, totalRow: true}
                ]]
                ,where:{
                    batchNo:data.value
                }
            });


            //赋值到 input框之中
            (function () {
                var url =BaseUrl+"assessEvaluate/myList";
                var param ={
                    batchNo:data.value,
                };
                $.ajax({
                    url: url,
                    type: "get",
                    async: false,
                    data: param,
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            $("#classeName").val(result.data.classeName);
                            $("#classeTeacherName").val(result.data.teacherName);
                            $("#evaluateContent").val(result.data.evaluateContent);
                        }else {
                            layerCustom.redCryMsg(result.msg);
                        }
                    }
                });
            })();
        });






    });
</script>
</body>
</html>

