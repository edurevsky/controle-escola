package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.services.ProfessorService;
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
public class ProfessorViewController {

    private final ProfessorService professorService;
    private static final int pageSize = 10;

    @Autowired
    public ProfessorViewController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(value = "/professores")
    public String index(Model model) {
        return paginatedProfessores(1, model);
    }

    @GetMapping(value = "/professores/{page}")
    public String paginatedProfessores(@PathVariable("page") Integer page, Model model) {
        Page<Professor> professoresPage = professorService.findPaginated(page, pageSize);
        List<Professor> professoresList = professoresPage.getContent();

        // Title
        model.addAttribute("title", "Lista de Professores");

        // Pagination
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", professoresPage.getTotalPages());
        model.addAttribute("totalItems", professoresPage.getTotalElements());

        model.addAttribute("professoresList", professoresList);
        return "professores/index";
    }

    @GetMapping(value = "/professores/registrar")
    public String addProfessorView(ProfessorForm professorForm, Model model) {
        model.addAttribute("title", "Registrar Professor");
        return "professores/new";
    }

    @PostMapping(value = "/professores/registrar")
    public String addProfessorPost(@ModelAttribute ProfessorForm professorForm) {
        professorService.save(professorForm);
        return "redirect:/professores";
    }

    @GetMapping(value = "/professores/{id}/editar")
    public String editarProfessor(@PathVariable Long id, ProfessorForm professorForm, Model model) {
        model.addAttribute("title", "Editar Professor");
        model.addAttribute("professor", professorService.findById(id));
        return "professores/edit";
    }

    @PostMapping(value = "/professores/{id}/editar")
    public String editarProfessorPost(@PathVariable Long id, @ModelAttribute ProfessorForm professorForm) {
        professorService.update(id, professorForm);
        return "redirect:/professores";
    }

    @GetMapping(value = "/professores/{id}/deletar")
    public String deletarProfessor(@PathVariable Long id) {
        professorService.remove(id);
        return "redirect:/professores";
    }

    @GetMapping(value = "/professores/{id}/detalhes")
    public String detalhesProfessor(@PathVariable Long id, Model model) {
        Professor professor = professorService.findById(id);
        model.addAttribute("title", String.format("Detalhes de %s", professor.getNome()));
        model.addAttribute("professor", professor);
        return "professores/details";
    }

}
