package me.edurevsky.controleescola.web.controllers;

import me.edurevsky.controleescola.configuration.security.LoggedUser;
import me.edurevsky.controleescola.entities.UserDetailsImpl;
import me.edurevsky.controleescola.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping(value = "/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/")
    public ModelAndView index(@LoggedUser UserDetailsImpl user) {
        ModelAndView mv = new ModelAndView("index");
        if (isProfessor(user)) {
            mv.addObject("professor", professorService.findByEmail(user.getUsername()));
        }
        if (isAluno(user)) {
            ;
        }
        mv.addObject("title", "Início");
        return mv;
    }

    private boolean isProfessor(UserDetailsImpl user) {
        return user.getUsername().contains("@professor");
    }

    private boolean isAluno(UserDetailsImpl user) {
        return user.getUsername().contains("@aluno");
    }
}
