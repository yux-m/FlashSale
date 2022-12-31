package yuxm.flashsale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used to test.
 */
@Controller
@RequestMapping("test")
public class DemoController {

    /**
     * Direct to a testing page (~/test/hi).
     *
     * @param model model
     * @return String
     **/
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("name", "Yuxin");
        return "hi";
    }
}
