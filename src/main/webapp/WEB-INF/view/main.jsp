<%--
  Created by IntelliJ IDEA.
  User: BestClever
  Date: 2020-07-03 0003
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/tag.jsp" %>
<html>
<head>
    <title>主页面</title>
    <link rel="stylesheet" href="${baseurl}/static/lib/yueqain/css/yueqian.all.css"/>
</head>
<body class="yueqian-container">

<blockquote class="layui-elem-quote layui-bg-green">
    <div id="nowTime"></div>
</blockquote>

<div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header">最新公告<i class="layui-icon">&#xe756;</i></div>
            <table class="layui-table" lay-skin="line">
                <tbody id="LAY_content"></tbody>
            </table>
        </div>

    </div>
</div>

<script id="LAY_tpl" type="text/html">
    {{# layui.each(d, function(index, item){ }}
    <tr onclick="viewNews('{{item.id}}')" style="cursor: pointer;">
        <td align="left"><a href="javascript:;"> {{item.noticeTitle}}</a></td>
        <td>{{item.createTime}}</td>
    </tr>
    {{# }); }}
</script>

<script src="${baseurl}/static/lib/layui/layui.js" charset="utf-8"></script>
<script src="${baseurl}/static/lib/yueqain/yueqian.all.js" charset="utf-8"></script>
<script>

    //获取当前登陆人
    var currentUserName='${activerUser.userName}';

    //获取系统时间
    var newDate = '';
    getLangDate();
    //值小于10时，在前面补0
    function dateFilter(date){
        if(date < 10){return "0"+date;}
        return date;
    }
    function getLangDate(){
        var dateObj = new Date(); //表示当前系统时间的Date对象
        var year = dateObj.getFullYear(); //当前系统时间的完整年份值
        var month = dateObj.getMonth()+1; //当前系统时间的月份值
        var date = dateObj.getDate(); //当前系统时间的月份中的日
        var day = dateObj.getDay(); //当前系统时间中的星期值
        var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
        var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
        var hour = dateObj.getHours(); //当前系统时间的小时值
        var minute = dateObj.getMinutes(); //当前系统时间的分钟值
        var second = dateObj.getSeconds(); //当前系统时间的秒钟值
        var timeValue = "" +((hour >= 12) ? (hour >= 18) ? " 晚上" : " 下午" : " 上午" ); //当前时间属于上午、晚上还是下午
        newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
        document.getElementById("nowTime").innerHTML = "亲爱的，"+currentUserName+"  "+timeValue+"好！欢迎使用后台管理系统。当前时间为： "+newDate+"　"+week;
        setTimeout("getLangDate()",1000);
    }

    //JavaScript代码区域
    layui.use(['element', 'table', 'form', 'jquery', 'layer', 'layerCustom','laytpl'], function () {
        var element = layui.element,
            table = layui.table,
            $ = layui.jquery,
            layerCustom = layui.layerCustom,
            layer = layui.layer,
            laytpl = layui.laytpl,
            form = layui.form;


        (function () {
            $.get(BaseUrl+'sysNotice/list', {limit: 5,orderByColumn:"createTime"}, function (result) {
                renderList(result.data);
            })
        })();

        function renderList(data) {
            var getTpl = document.getElementById("LAY_tpl").innerHTML;
            laytpl(getTpl).render(data, function (html) {
                document.getElementById("LAY_content").innerHTML = html;
            });
        }


        window.viewNews = function (id) {
            layerCustom.open("查看公告信息", BaseUrl + 'sysNotice/viewNotice?id='+id, "950px", "580px", function (layero, index) {
            })
        }
    });

</script>

</body>
</html>
