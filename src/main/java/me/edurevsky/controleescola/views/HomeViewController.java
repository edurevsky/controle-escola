package me.edurevsky.controleescola.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

}
