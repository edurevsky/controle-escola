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
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("title", "Lista de Alunos");
        model.addAttribute("alunosList", alunoService.findAll());
        return "alunos/index";
    }

    @GetMapping(value = "/alunos/registrar")
    public String novoAlunoView(AlunoForm alunoForm, Model model) {
        model.addAttribute("title", "Registrar Aluno");
        model.addAttribute("turmasList", turmaService.findAll());
        model.addAttribute("turnos", Turno.values());
        return "alunos/new";
    }

    @PostMapping(value = "/alunos/registrar")
    public String novoAlunoPost(@ModelAttribute AlunoForm alunoForm, Model model) {
        alunoService.save(alunoForm);
        return "redirect:/alunos";
    }

    @GetMapping(value = "/alunos/{id}/deletar")
    public String deleteAluno(@PathVariable("id") Long id) {
        alunoService.remove(id);
        return "redirect:/alunos";
    }

    @GetMapping(value = "/alunos/{id}/editar")
    public String editAlunoView(@PathVariable("id") Long id, AlunoForm alunoForm, Model model) {
        model.addAttribute("title", "Editar Aluno");
        model.addAttribute("aluno", alunoService.findById(id));
        model.addAttribute("turnos", Turno.values());
        model.addAttribute("turmasList", turmaService.findAll());
        return "alunos/edit";
    }

    @PostMapping(value = "/alunos/{id}/editar")
    public String editAlunoPost(@PathVariable("id") Long id, @ModelAttribute AlunoForm alunoForm) {
        alunoService.update(id, alunoForm);
        return "redirect:/alunos";
    }

}
