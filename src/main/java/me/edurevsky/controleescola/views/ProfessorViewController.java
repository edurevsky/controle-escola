package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfessorViewController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorViewController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(value = "/professores")
    public String index(Model model) {
        model.addAttribute("title", "Lista de Professores");
        model.addAttribute("professoresList", professorService.findAll());
        return "professores/index";
    }

    @GetMapping(value = "/professores/registrar")
    public String addProfessorView(ProfessorForm professorForm, Model model) {
        model.addAttribute("title", "Registrar Professor");
        return "professores/addprofessor";
    }

    @PostMapping(value = "/professores/registrar")
    public String addProfessorPost(@ModelAttribute ProfessorForm professorForm) {
        professorService.save(professorForm);
        return "redirect:/professores";
    }

}
