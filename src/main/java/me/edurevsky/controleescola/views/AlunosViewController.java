package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.forms.AlterarTurmaForm;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.forms.EditAlunoForm;
import me.edurevsky.controleescola.services.AlunoService;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/alunos")
public class AlunosViewController {

    private final AlunoService alunoService;
    private final TurmaService turmaService;
    private static final int PAGE_SIZE = 10;

    @Autowired
    public AlunosViewController(final AlunoService alunoService, final TurmaService turmaService) {
        this.alunoService = alunoService;
        this.turmaService = turmaService;
    }

    @GetMapping
    public ModelAndView index() {
        return this.paginatedAlunos(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedAlunos(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("alunos/index");
        Page<Aluno> alunosPage = alunoService.findPaginated(page, PAGE_SIZE);
        List<Aluno> alunosList = alunosPage.getContent();

        // Title
        mv.addObject("title", "Lista de Alunos");

        mv.addObject("currentPage", page);
        mv.addObject("totalPages", alunosPage.getTotalPages());
        // Pagination
        mv.addObject("totalItems", alunosPage.getTotalElements());

        mv.addObject("alunosList", alunosList);
        return mv;
    }

    @GetMapping(value = "/registrar")
    public ModelAndView newAlunoGet(AlunoForm alunoForm) {
        ModelAndView mv = new ModelAndView("alunos/new");
        mv.addObject("title", "Registrar Aluno");
        mv.addObject("turmasList", turmaService.findAll());
        mv.addObject("turnos", Turno.values());
        return mv;
    }

    @PostMapping(value = "/registrar")
    public ModelAndView newAlunoPost(@Valid @ModelAttribute AlunoForm alunoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.newAlunoGet(alunoForm);
        }
        alunoService.save(alunoForm);
        return new ModelAndView("redirect:/alunos");
    }

    @GetMapping(value = "/{id}/deletar")
    public ModelAndView deleteAluno(@PathVariable("id") Long id) {
        alunoService.remove(id);
        return new ModelAndView("redirect:/alunos");
    }

    @GetMapping(value = "/{id}/editar")
    public ModelAndView editAlunoGet(@PathVariable("id") Long id, EditAlunoForm alunoForm) {
        Aluno aluno = alunoService.findById(id);
        if (Objects.isNull(aluno)) return new ModelAndView("redirect:/alunos");

        alunoForm.loadFromAluno(aluno);
        ModelAndView mv = new ModelAndView("alunos/edit");
        mv.addObject("title", "Editar Aluno");
        mv.addObject("id", id);
        mv.addObject("cpf", aluno.getCpf());
        mv.addObject("turnos", Turno.values());
        mv.addObject("turmasList", turmaService.findAll());
        return mv;
    }

    @PostMapping(value = "/{id}/editar")
    public ModelAndView editAlunoPost(@PathVariable("id") Long id, @Valid @ModelAttribute EditAlunoForm alunoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.editAlunoGet(id, alunoForm);
        }
        alunoService.update(id, alunoForm);
        return new ModelAndView("redirect:/alunos");
    }

    @GetMapping(value = "/{id}/detalhes")
    public ModelAndView detailsAluno(@PathVariable("id") Long id) {
        Aluno aluno = alunoService.findById(id);
        ModelAndView mv = new ModelAndView("alunos/details");
        mv.addObject("title", String.format("Detalhes de %s", aluno.getNome()));
        mv.addObject("aluno", aluno);
        mv.addObject("mstatus", !aluno.getEstaAtivo() ? "Ativo" : "Inativo");
        return mv;
    }

    @GetMapping(value = "/{id}/alterar-status")
    public String switchStatus(@PathVariable("id") Long id) {
        alunoService.switchEstaAtivo(id);
        return "redirect:/alunos/{id}/detalhes";
    }

    @GetMapping(value = "/{id}/alterar-turma")
    public ModelAndView changeTurmaGet(@PathVariable("id") Long id, AlterarTurmaForm turmaForm) {
        turmaForm.loadFromAluno(alunoService.findById(id));
        ModelAndView mv = new ModelAndView("alunos/changeturma");
        mv.addObject("id", id);
        mv.addObject("turmasList", turmaService.findAll());
        return mv;
    }

    @PostMapping(value = "/{id}/alterar-turma")
    public ModelAndView changeTurmaPost(@PathVariable("id") Long id, @ModelAttribute AlterarTurmaForm turmaForm) {
        alunoService.addTurma(id, turmaForm);
        return new ModelAndView("redirect:/alunos/{id}/detalhes");
    }

}
