package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.forms.AlterarTurmaForm;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.services.AlunoService;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AlunosViewController {

    private final AlunoService alunoService;
    private final TurmaService turmaService;
    private static final int pageSize = 10;

    @Autowired
    public AlunosViewController(AlunoService alunoService, TurmaService turmaService) {
        this.alunoService = alunoService;
        this.turmaService = turmaService;
    }

    @GetMapping(value = "/alunos")
    public String index(Model model) {
        return paginatedAlunos(1, model);
    }

    @GetMapping(value = "/alunos/{page}")
    public String paginatedAlunos(@PathVariable("page") Integer page, Model model) {
        Page<Aluno> alunosPage = alunoService.findPaginated(page, pageSize);
        List<Aluno> alunosList = alunosPage.getContent();

        // Title
        model.addAttribute("title", "Lista de Alunos");

        // Pagination
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", alunosPage.getTotalPages());
        model.addAttribute("totalItems", alunosPage.getTotalElements());

        model.addAttribute("alunosList", alunosList);
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
    public ModelAndView novoAlunoPost(@Valid @ModelAttribute AlunoForm alunoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("alunos/new");
            mv.addObject("title", "Registrar Aluno");
            mv.addObject("turmasList", turmaService.findAll());
            mv.addObject("turnos", Turno.values());
            return mv;
        }
        alunoService.save(alunoForm);
        return new ModelAndView("redirect:/alunos");
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

    @GetMapping(value = "/alunos/{id}/detalhes")
    public String detailsAluno(@PathVariable("id") Long id, Model model) {
        Aluno aluno = alunoService.findById(id);
        model.addAttribute("title", String.format("Detalhes de %s", aluno.getNome()));
        model.addAttribute("aluno", aluno);
        model.addAttribute("mstatus", !aluno.getEstaAtivo() ? "Ativo" : "Inativo");
        return "alunos/details";
    }

    @GetMapping(value = "/alunos/{id}/alterar-status")
    public String switchStatus(@PathVariable("id") Long id) {
        alunoService.switchEstaAtivo(id);
        return "redirect:/alunos/{id}/detalhes";
    }

    @GetMapping(value = "/alunos/{id}/alterar-turma")
    public String changeTurmaGet(@PathVariable("id") Long id, AlterarTurmaForm turmaForm, Model model) {
        model.addAttribute("aluno", alunoService.findById(id));
        model.addAttribute("turmasList", turmaService.findAll());
        return "alunos/changeturma";
    }

    @PostMapping(value = "/alunos/{id}/alterar-turma")
    public String changeTurmaPost(@PathVariable("id") Long id, @ModelAttribute AlterarTurmaForm turmaForm) {
        alunoService.addTurma(id, turmaForm);
        return "redirect:/alunos/{id}/detalhes";
    }

}
