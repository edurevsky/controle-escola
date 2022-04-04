package me.edurevsky.controleescola.web.controllers;

import me.edurevsky.controleescola.security.UserDetailsImpl;
import me.edurevsky.controleescola.security.LoggedUser;
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
    public ModelAndView index(@LoggedUser UserDetailsImpl userDetails) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", userDetails);
        return mv;
    }
}
