package com.plethora.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home (@RequestParam(name = "title",required = false, defaultValue = "Omega")String name, Model model){

        model.addAttribute("title","MAIN PAGE");
        return "home";
    }

    @GetMapping("/about")
    public String about( Model model){
        model.addAttribute("title","Об авторе");
        return "about";
    }

}
