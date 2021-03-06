package me.edurevsky.controleescola.web.controllers;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.forms.AlterarTurmaForm;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.forms.EditAlunoForm;
import me.edurevsky.controleescola.services.AlunoService;
import me.edurevsky.controleescola.services.AppUserService;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunosController {

    private final AlunoService alunoService;
    private final TurmaService turmaService;
    private final AppUserService appUserService;
    private static final int PAGE_SIZE = 10;

    @GetMapping
    public ModelAndView index() {
        return this.paginatedAlunos(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedAlunos(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("alunos/index");
        Page<Aluno> alunosPage = alunoService.findPaginated(page, PAGE_SIZE);
        List<Aluno> alunosList = alunosPage.getContent();

        mv.addObject("title", "Lista de Alunos");

        mv.addObject("currentPage", page);
        mv.addObject("totalPages", alunosPage.getTotalPages());
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
            return newAlunoGet(alunoForm);
        }
        Aluno aluno = alunoService.save(alunoForm);
        appUserService.saveAluno(aluno);
        return redirect();
    }

    @GetMapping(value = "/{id}/deletar")
    public ModelAndView deleteAluno(@PathVariable("id") Long id) {
        alunoService.remove(id);
        return redirect();
    }

    @GetMapping(value = "/{id}/editar")
    public ModelAndView editAlunoGet(@PathVariable("id") Long id, EditAlunoForm alunoForm) {
        Aluno aluno = alunoService.findById(id);
        if (Objects.isNull(aluno)) {
            return redirect();
        }
        alunoForm.loadFromAluno(aluno);
        ModelAndView mv = new ModelAndView("alunos/edit");
        mv.addObject("title", "Editar Aluno");
        mv.addObject("id", id);
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
        return redirect();
    }

    @GetMapping(value = "/{id}/detalhes")
    public ModelAndView detailsAluno(@PathVariable("id") Long id) {
        Aluno aluno = alunoService.findById(id);
        ModelAndView mv = new ModelAndView("alunos/details");
        mv.addObject("title", String.format("Detalhes de %s", aluno.getNome()));
        mv.addObject("aluno", aluno);
        mv.addObject("mstatus", Boolean.FALSE.equals(aluno.getEstaAtivo()) ? "Ativo" : "Inativo");
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

    private ModelAndView redirect() {
        return new ModelAndView("redirect:/alunos");
    }
}
