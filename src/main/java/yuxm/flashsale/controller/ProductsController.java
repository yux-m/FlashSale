package yuxm.flashsale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import yuxm.flashsale.entity.User;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/products")
public class ProductsController {

    /**
     * Derect to product list page.
     *
     * @param session
     * @param model
     * @param ticket
     * @return
     */
    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
        if (ticket.isEmpty()) {
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "productList";

    }

}
