package com.demo.springmvc3.controller;

import com.demo.springmvc3.model.User;
import com.demo.springmvc3.service.UserDetailServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private UserDetailServiceimpl userDetailServiceimpl;

    public AuthController(UserDetailServiceimpl userDetailServiceimpl) {
        this.userDetailServiceimpl = userDetailServiceimpl;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute( "user", new User(  ) );
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(User user, RedirectAttributes redirectAttributes){

       /* if(result.hasErrors()){
            model.addAttribute( "user", user );
            return "auth/register";
        }
*/
        userDetailServiceimpl.register( user );
        redirectAttributes.addFlashAttribute( "register", true );
        return "redirect:/products";
    }
}
