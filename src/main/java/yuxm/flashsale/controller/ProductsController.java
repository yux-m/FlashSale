package yuxm.flashsale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import yuxm.flashsale.entity.User;

@Controller
@RequestMapping("/products")
public class ProductsController {

//    @Autowired
//    private IUserService userService;

    /**
     * Direct to product list page.
     */
    @RequestMapping("/toList")
    public String toList(Model model, User user) {
//        if (ticket.isEmpty()) {
//            return "login";
//        }
//        User user = userService.getUserByCookie(ticket, request, response);
//        //User user = (User) session.getAttribute(ticket);
//        if (user == null) {
//            return "login";
//        }
        model.addAttribute("user", user);
        return "productList";

    }

}
