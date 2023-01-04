package yuxm.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import yuxm.flashsale.entity.User;
import yuxm.flashsale.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private IUserService userService;

    /**
     * Direct to product list page.
     */
    @RequestMapping("/toList")
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String ticket) {
        if (ticket.isEmpty()) {
            return "login";
        }
        User user = userService.getUserByCookie(ticket, request, response);
        //User user = (User) session.getAttribute(ticket);
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "productList";

    }

}
