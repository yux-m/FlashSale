package yuxm.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.service.IProductService;
import yuxm.flashsale.vo.ProductVO;

import java.util.Date;

@Controller
@RequestMapping("/products")
public class ProductsController {

    //    @Autowired
//    private IUserService userService;
    @Autowired
    private IProductService productService;

    /**
     * Direct to product list page.
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("productList", productService.findProductVO());
        return "productList";

    }

    @RequestMapping(value = "/toDetail/{productId}")
    public String toDetail(Model model, User user, @PathVariable Long productId) {
        model.addAttribute("user", user);
        ProductVO productVO = productService.findProductVoByProductId(productId);
        Date startDate = productVO.getStartDate();
        Date endDate = productVO.getEndDate();
        Date nowDate = new Date();
        //status
        int flashsaleStatus = 0;
        //count down
        int remainSeconds = 0;

        if (nowDate.before(startDate)) {
            //not yet started
            remainSeconds = (int) ((startDate.getTime() - nowDate.getTime()) / 1000);
        } else if (nowDate.after(endDate)) {
            //expired
            flashsaleStatus = 2;
            remainSeconds = -1;
        } else {
            //on sale
            flashsaleStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("product", productVO);
        model.addAttribute("flashsaleStatus", flashsaleStatus);
        return "productDetail";
    }

}
