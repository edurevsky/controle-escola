package me.edurevsky.controleescola.web.controllers;

import me.edurevsky.controleescola.configuration.security.LoggedUser;
import me.edurevsky.controleescola.entities.AvisosTurma;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.entities.UserDetailsImpl;
import me.edurevsky.controleescola.forms.AvisosTurmaForm;
import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.services.AvisosTurmaService;
import me.edurevsky.controleescola.services.ProfessorService;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/turmas")
public class TurmasController {

    private final TurmaService turmaService;
    private final ProfessorService professorService;
    private final AvisosTurmaService avisosTurmaService;
    private static final int PAGE_SIZE = 10;

    @Autowired
    public TurmasController(final TurmaService turmaService, final ProfessorService professorService, AvisosTurmaService avisosTurmaService) {
        this.turmaService = turmaService;
        this.professorService = professorService;
        this.avisosTurmaService = avisosTurmaService;
    }

    @GetMapping
    public ModelAndView index() {
        return paginatedTurmas(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedTurmas(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("turmas/index");
        Page<Turma> turmasPage = turmaService.findPaginated(page, PAGE_SIZE);
        List<Turma> turmasList = turmasPage.getContent();

        mv.addObject("title", "Lista de Turmas");

        mv.addObject("currentPage", page);
        mv.addObject("totalItems", turmasPage.getTotalElements());
        mv.addObject("totalPages", turmasPage.getTotalPages());

        mv.addObject("turmasList", turmasList);
        return mv;
    }

    @GetMapping(value = "/registrar")
    public ModelAndView newTurmaGet(TurmaForm turmaForm) {
        ModelAndView mv = new ModelAndView("turmas/new");
        mv.addObject("title", "Registrar Turma");
        mv.addObject("professoresList", professorService.findAll());
        return mv;
    }

    @PostMapping(value = "/registrar")
    public ModelAndView newTurmaPost(@ModelAttribute TurmaForm turmaForm) {
        turmaService.save(turmaForm);
        return redirect();
    }

    @GetMapping(value = "/{id}/detalhes")
    public ModelAndView detailsTurma(@PathVariable Long id) {
        Turma turma = turmaService.findById(id);
        ModelAndView mv = new ModelAndView("turmas/details");
        mv.addObject("title", String.format("Detalhes da turma %s", turma.getTurma()));
        mv.addObject("turma", turma);
        mv.addObject("ultimoAviso", avisosTurmaService.findLastByTurma(turma));
        return mv;
    }

    @GetMapping(value = "/{id}/editar")
    public ModelAndView editTurmaGet(@PathVariable Long id, TurmaForm turmaForm) {
        Turma turma = turmaService.findById(id);
        if (Objects.isNull(turma)) {
            return redirect();
        }

        turmaForm.loadFromTurma(turma);
        ModelAndView mv = new ModelAndView("turmas/edit");
        mv.addObject("title", "Editar Turma");
        mv.addObject("id", id);
        mv.addObject("professoresList", professorService.findAll());
        return mv;
    }

    @PostMapping(value = "/{id}/editar")
    public ModelAndView editTurmaPost(@PathVariable Long id, @ModelAttribute TurmaForm turmaForm) {
        turmaService.update(id, turmaForm);
        return redirect();
    }

    @GetMapping(value = "/{id}/deletar")
    public ModelAndView deleteTurma(@PathVariable Long id) {
        turmaService.delete(id);
        return redirect();
    }

    @GetMapping(value = "/{id}/avisos")
    public ModelAndView turmaAvisos(@PathVariable Long id) {
        Turma turma = turmaService.findById(id);
        if (Objects.isNull(turma)) {
            return redirect();
        }
        ModelAndView mv = new ModelAndView("turmas/avisos/all");
        mv.addObject("avisosList", avisosTurmaService.findByTurmaSorted(turma));
        return mv;
    }

    @GetMapping(value = "/{id}/avisos/novo")
    public ModelAndView newAvisoGet(@PathVariable Long id, AvisosTurmaForm avisosTurmaForm) {
        ModelAndView mv = new ModelAndView("turmas/avisos/new");
        mv.addObject("title", "Publicar novo aviso");
        return mv;
    }

    @PostMapping(value = "/{id}/avisos/novo")
    public ModelAndView newAvisoPost(@LoggedUser UserDetailsImpl currentUser, @PathVariable Long id, @ModelAttribute AvisosTurmaForm avisosTurmaForm) {
        avisosTurmaService.save(avisosTurmaForm, id, currentUser);
        return new ModelAndView("redirect:/turmas/{id}/detalhes");
    }

    private ModelAndView redirect() {
        return new ModelAndView("redirect:/turmas");
    }
}
