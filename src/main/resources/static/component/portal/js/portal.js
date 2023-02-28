$("#search").on("click",function () {
    // 查询后台 跳转到查询结果界面
});

/*屏幕滚动 设置导航栏固定顶部*/
$(window).scroll(function() {
    var h = $("body").height() - window.screen.height;
    //console.log(h);
    if ($(window).scrollTop() > 28 && h > 120) {
        $(".topnav").addClass("is-fixed").find("").fadeOut(400);
    } else if ($(window).scrollTop() < 28) {
        $(".topnav").removeClass("is-fixed").find("").fadeIn(400);
    }
});