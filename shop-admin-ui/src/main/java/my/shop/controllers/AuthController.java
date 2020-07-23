package my.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class AuthController {

    @GetMapping("")
    public String indexPage(){
        return "admin";
    }

    @GetMapping(value = "login")
    public String getLoginPage(){
        return "login";
    }

}
