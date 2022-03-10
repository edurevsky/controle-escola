package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.services.AlunoService;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AlunosViewController {

    private final AlunoService alunoService;
    private final TurmaService turmaService;

    @Autowired
    public AlunosViewController(AlunoService alunoService, TurmaService turmaService) {
        this.alunoService = alunoService;
        this.turmaService = turmaService;
    }

    @GetMapping(value = "/alunos")
    public String index(Model model) {
        model.addAttribute("title", "Alunos Registrados");
        model.addAttribute("alunosList", alunoService.findAll());
        return "alunos/index";
    }

    @GetMapping(value = "/alunos/registrar")
    public String novoAlunoView(AlunoForm alunoForm, Model model) {
        model.addAttribute("title", "Registrar Aluno");
        model.addAttribute("turmasList", turmaService.findAll());
        model.addAttribute("matutino", Turno.MATUTINO);
        model.addAttribute("vespertino", Turno.VESPERTINO);
        model.addAttribute("noturno", Turno.NOTURNO);
        return "alunos/addaluno";
    }

    @PostMapping(value = "/alunos/registrar")
    public String novoAlunoPost(@ModelAttribute AlunoForm alunoForm, Model model) {
        alunoService.save(alunoForm);
        return "redirect:/alunos";
    }

}