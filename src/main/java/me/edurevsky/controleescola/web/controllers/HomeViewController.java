package me.edurevsky.controleescola.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeViewController {

    @GetMapping(value = "/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
