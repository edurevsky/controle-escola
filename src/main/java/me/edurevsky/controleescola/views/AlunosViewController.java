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
    private static final int PAGE_SIZE = 10;

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
        Page<Aluno> alunosPage = alunoService.findPaginated(page, PAGE_SIZE);
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
    public ModelAndView novoAlunoView(AlunoForm alunoForm) {
        ModelAndView mv = new ModelAndView("alunos/new");
        mv.addObject("title", "Registrar Aluno");
        mv.addObject("turmasList", turmaService.findAll());
        mv.addObject("turnos", Turno.values());
        return mv;
    }

    @PostMapping(value = "/alunos/registrar")
    public ModelAndView novoAlunoPost(@Valid @ModelAttribute AlunoForm alunoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return novoAlunoView(alunoForm);
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
    public ModelAndView editAlunoView(@PathVariable("id") Long id, AlunoForm alunoForm) {
        Aluno aluno = alunoService.findById(id);
        if (aluno == null) return new ModelAndView("redirect:/alunos");

        alunoForm.loadFromAluno(aluno);
        ModelAndView mv = new ModelAndView("alunos/edit");
        mv.addObject("title", "Editar Aluno");
        mv.addObject("id", id);
        mv.addObject("turnos", Turno.values());
        mv.addObject("turmasList", turmaService.findAll());
        return mv;
    }

    @PostMapping(value = "/alunos/{id}/editar")
    public ModelAndView editAlunoPost(@PathVariable("id") Long id, @Valid @ModelAttribute AlunoForm alunoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return editAlunoView(id, alunoForm);
        }
        alunoService.update(id, alunoForm);
        return new ModelAndView("redirect:/alunos");
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
    public ModelAndView changeTurmaGet(@PathVariable("id") Long id, AlterarTurmaForm turmaForm) {
        turmaForm.loadFromAluno(alunoService.findById(id));
        ModelAndView mv = new ModelAndView("alunos/changeturma");
        mv.addObject("id", id);
        mv.addObject("turmasList", turmaService.findAll());
        return mv;
    }

    @PostMapping(value = "/alunos/{id}/alterar-turma")
    public String changeTurmaPost(@PathVariable("id") Long id, @ModelAttribute AlterarTurmaForm turmaForm) {
        alunoService.addTurma(id, turmaForm);
        return "redirect:/alunos/{id}/detalhes";
    }

}
