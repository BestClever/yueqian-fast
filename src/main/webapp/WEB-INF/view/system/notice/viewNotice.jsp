<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>消息通知表列表</title>

    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css" media="all">
</head>
<body class="yueqian-container">

<!--查看新闻弹出层-->
<div id="LAY_content" style="padding: 10px">

</div>

<script id="LAY_tpl" type="text/html">
    <h3 id="news_title" style="text-align: center">{{d.noticeTitle}}</h3>
    <div style="text-align: center;margin-top: 2%;font-size: 12px;color: grey">
        发布人：<span id="createUserName">{{d.createUserName}}</span>
        发布时间：<span id="createTime">{{d.createTime}}</span>
    </div>
    <!-- <hr>-->
    <div id="news_content" style="text-indent: 2em;padding: 3% ">{{d.noticeContent}}</div>
</script>


<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'layerCustom', 'laytpl'], function () {
        var element = layui.element,
            table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            laytpl = layui.laytpl,
            form = layui.form;
        (function () {
            $.get(BaseUrl+'sysNotice/selectSysNoticeById', {id: '${id}'}, function (result) {
                renderList(result.data);
            })
        })();

        function renderList(data) {
            var getTpl = document.getElementById("LAY_tpl").innerHTML;
            laytpl(getTpl).render(data, function (html) {
                document.getElementById("LAY_content").innerHTML = html;
            });
        }
    });
</script>
</body>
</html>


