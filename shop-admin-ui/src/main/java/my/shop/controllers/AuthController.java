package my.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class AdminController {

    @GetMapping("admin")
    public String indexPage(){
        return "admin";
    }

    @GetMapping(value = "login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping(value = "")
    public String getHomePage(){
        return "home";
    }

//    @GetMapping(value = "authenticateTheUser")
//    public String getHomePage2(){
//        return "home";
//    }
}
