package ru.javaschool.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {

    @RequestMapping("")
    public String startPage() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/error403")
    public String errorPage() {
        return "error403";
    }

    @RequestMapping("/")
    public String indexPageSub() {
        return "index";
    }
}
