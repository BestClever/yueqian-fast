<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>学生评价</title>

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

<div class="layui-card">
    <div class="layui-card-body">
        <div style=" display: flex;flex-direction: column; justify-content: center;align-items: center">
            <div><h1 style="letter-spacing:3px">班主任工作测评表（班级学生测评）（满分100分）</h1></div>
            <div style="margin-top: 10px"><span id="grade">${classe.classeGrade}</span> 年级 <sapn id="classes">${classe.classeNo}</sapn> 班</div>
        </div>
        <table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
    </div>
</div>

<%--学生评价--%>
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="" id="LAY_Form">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label layui-required">评价内容</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入评价内容" name="evaluateContent" id="evaluateContent" class="layui-textarea" lay-verify="required"></textarea>
                </div>
            </div>
            <div style="display: flex;flex-direction: row-reverse;margin-top: 10px">
                <button class="yueqian-btn yueqian-btn-md yueqian-btn-primary" lay-submit lay-filter="search">
                    <i class="layui-icon layui-icon-search"></i>
                    提交
                </button>
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
                        $("#batchNameSelect").append("<option  value=>请选择批次</option>");
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
        });

        var myTalbe = table.render({
            elem: '#tableId'
            , url: BaseUrl + 'assess/list'
            , skin: 'line'
            ,totalRow: true
            , cols: [[
                {type:'numbers', totalRowText: '合计'}
                ,{field: 'assessContent', title: '考  核  内  容（每一项满分10分）', width:900}
                , {field: 'assessScore', title: '得分', edit: 'text', width:200, totalRow: true}
            ]]
            ,where:{
                assessType:3
            }

        });

        form.on("submit(search)", function (data) {
            var url =BaseUrl+"assessEvaluate/submitEvaluate";
            var params ={
                evaluateContent:$("#evaluateContent").val(),
                evaluateType:"3",
                batchNo:$("#batchNameSelect").val(),
                batchName:$("#batchName").val(),
            };
            common.ajax.post(url,params,function (result) {
                if (result.success) {
                    layerCustom.greenLaughMsg(result.msg,function () {
                        $("#LAY_Form")[0].reset();
                        layui.form.render();
                    })
                } else {
                    layerCustom.redCryMsg(result.msg);
                }
            });
            return false;
        });

        //监听单元格编辑
        table.on('edit(tableFilter)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            if (common.isEmpty($("#batchName").val())) {
                layerCustom.yellowQuestionMsg("请选择评价阶段");
                return
            }

            if(typeof value != 'number' && isNaN(value)){
                layerCustom.greenLaughMsg("只能填写数字");
                return
            }
            if(value<0||value>10){
                layerCustom.greenLaughMsg("只能输入0~10之间的分数");
                return
            }
            var tableArr = layui.table.cache["tableId"];//获取表格中所有的值
            var scores = new Array();
            for (i=0;i<tableArr.length;i++){
                var score =tableArr[i].assessScore;
                if (!common.isEmpty(score)) {
                    scores.push(score);
                }
            }
            var scoreCount =eval(scores.join("+"))
            $('.layui-table-total td[data-field="assessScore"] .layui-table-cell').text(scoreCount);
            //提交到后台进行 存储
            var params = {
                batchNo:$("#batchNameSelect").val(),
                batchName:$("#batchName").val(),
                assessId:data.id,
                assessContent:data.assessContent,
                assessScore:value
            }
            var url =BaseUrl+ "assessResulitem/saveItme";
            $.post(url,params,function (result) {
                if (result.success) {
                } else {
                    layerCustom.redCryMsg(result.msg);
                }
            });
        });
    });
</script>
</body>
</html>

