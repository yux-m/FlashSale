package yuxm.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.service.IProductService;
import yuxm.flashsale.vo.ProductVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/products")
public class ProductsController {

    //    @Autowired
//    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    /**
     * Direct to product list page.
     */
    @RequestMapping(value = "/toList", produces = "text/html; charset=utf-8")
    @ResponseBody
    public String toList(Model model, User user, HttpServletRequest request, HttpServletResponse response) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //get the html page to be cached from Redis
        String html = (String) valueOperations.get("productList");  //we know we are caching html, so we can convert it to String directly
        //if not empty, return page
        if (html != null && !html.isEmpty()) return html;
        //if getting html fails, render manually
        model.addAttribute("user", user);
        model.addAttribute("productList", productService.findProductVO());
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("productList", webContext);
        if (html != null && !html.isEmpty()) {
            valueOperations.set("productList", html, 1, TimeUnit.MINUTES);  //timeout: 1min
        }
        return html;
    }

    @RequestMapping(value = "/toDetail/{productId}", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String toDetail(Model model, User user, @PathVariable Long productId, HttpServletRequest request, HttpServletResponse response) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //get the html page to be cached from Redis
        String html = (String) valueOperations.get("productDetail:" + productId);
        //if not empty, return page
        if (html != null && !html.isEmpty()) return html;
        //if getting html fails, render manually
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
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("productDetail", webContext);
        if (html != null && !html.isEmpty()) {
            valueOperations.set("productDetail" + productId, html, 1, TimeUnit.MINUTES);  //timeout: 1min
        }
        return html;
    }

}
