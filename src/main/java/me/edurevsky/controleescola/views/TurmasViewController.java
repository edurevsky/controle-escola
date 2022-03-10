package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.services.ProfessorService;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TurmasViewController {

    private final TurmaService turmaService;
    private final ProfessorService professorService;

    @Autowired
    public TurmasViewController(TurmaService turmaService, ProfessorService professorService) {
        this.turmaService = turmaService;
        this.professorService = professorService;
    }

    @GetMapping(value = "/turmas")
    public String index(Model model) {
        model.addAttribute("title", "Turmas Registradas");
        model.addAttribute("turmasList", turmaService.findAll());
        return "turmas/index";
    }

    @GetMapping(value = "/turmas/registrar")
    public String addTurmaView(TurmaForm turmaForm, Model model) {
        model.addAttribute("title", "Registrar Turma");
        model.addAttribute("professoresList", professorService.findAll());
        return "turmas/addturma";
    }

    @PostMapping(value = "turmas/registrar")
    public String addTurmaPost(@ModelAttribute TurmaForm turmaForm, Model model) {
        turmaService.save(turmaForm);
        return "redirect:/turmas";
    }

}
