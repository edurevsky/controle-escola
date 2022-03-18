package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.services.ProfessorService;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TurmasViewController {

    private final TurmaService turmaService;
    private final ProfessorService professorService;
    private static final int pageSize = 10;

    @Autowired
    public TurmasViewController(TurmaService turmaService, ProfessorService professorService) {
        this.turmaService = turmaService;
        this.professorService = professorService;
    }

    @GetMapping(value = "/turmas")
    public String index(Model model) {
        return paginatedTurmas(1, model);
    }

    @GetMapping(value = "/turmas/{page}")
    public String paginatedTurmas(@PathVariable("page") Integer page, Model model) {
        Page<Turma> turmasPage = turmaService.findPaginated(page, pageSize);
        List<Turma> turmasList = turmasPage.getContent();

        // Title
        model.addAttribute("title", "Lista de Turmas");

        // Pagination
        model.addAttribute("currentPage", page);
        model.addAttribute("totalItems", turmasPage.getTotalElements());
        model.addAttribute("totalPages", turmasPage.getTotalPages());

        model.addAttribute("turmasList", turmasList);
        return "turmas/index";
    }

    @GetMapping(value = "/turmas/registrar")
    public String addTurmaView(TurmaForm turmaForm, Model model) {
        model.addAttribute("title", "Registrar Turma");
        model.addAttribute("professoresList", professorService.findAll());
        return "turmas/new";
    }

    @PostMapping(value = "/turmas/registrar")
    public String addTurmaPost(@ModelAttribute TurmaForm turmaForm) {
        turmaService.save(turmaForm);
        return "redirect:/turmas";
    }

    @GetMapping(value = "/turmas/{id}/detalhes")
    public String detalhesTurmas(@PathVariable Long id, Model model) {
        Turma turma = turmaService.findById(id);
        model.addAttribute("title", String.format("Detalhes da turma %s", turma.getTurma()));
        model.addAttribute("turma", turma);
        return "turmas/details";
    }

    @GetMapping(value = "/turmas/{id}/editar")
    public String editarTurma(@PathVariable Long id, TurmaForm turmaForm, Model model) {
        model.addAttribute("title", "Editar Turma");
        model.addAttribute("turma", turmaService.findById(id));
        model.addAttribute("professoresList", professorService.findAll());
        return "turmas/edit";
    }

    @PostMapping(value = "/turmas/{id}/editar")
    public String editarTurmaPost(@PathVariable Long id, @ModelAttribute TurmaForm turmaForm) {
        turmaService.update(id, turmaForm);
        return "redirect:/turmas";
    }

    @GetMapping(value = "/turmas/{id}/deletar")
    public String deletarTurma(@PathVariable Long id) {
        turmaService.delete(id);
        return "redirect:/turmas";
    }

}
