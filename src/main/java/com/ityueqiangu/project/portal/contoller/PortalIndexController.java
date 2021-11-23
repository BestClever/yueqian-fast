package com.ityueqiangu.project.portal.contoller;

import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.project.carousel.domain.Carousel;
import com.ityueqiangu.project.carousel.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 前端控制类
 * @ProjectName: yueqian-fast
 * @PackageName: com.ityueqiangu.project.portal.contoller
 * @ClassName: IndexController
 * @FileName: IndexController.java
 * @CreateDate: 2021-11-17 16:21:57
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalIndexController {

    @Autowired
    private ICarouselService carouselService;

    /**
     * 登录
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 10:48:25
     */
    @GetMapping("/login")
    public String login() {
        return "portal/login";
    }

    /**
     * 注册
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 10:48:17
     */
    @GetMapping("/register")
    public String register() {
        return "portal/register";
    }

    /**
     * 跳转到主页
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月19日 0019 16:15:04
     */
    @GetMapping("/index")
    public String index(ModelMap mmp) {
        Carousel carousel = new Carousel();
        carousel.setType(Constants.FAIL);
        carousel.setIsDisplay(Constants.SUCCESS);
        List<Carousel> carousels = carouselService.selectCarouselList(carousel);
        mmp.addAttribute("carouselList", carousels);
        return "portal/index";
    }



    /**
     * 退出登录
     * @author FlowerStone
     * @date 2021年11月23日 0023 14:01:18
     * @param session
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("activerUser");
        return "redirect:index";
    }



}
