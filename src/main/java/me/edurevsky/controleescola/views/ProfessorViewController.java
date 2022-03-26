package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.services.ProfessorService;
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
public class ProfessorViewController {

    private final ProfessorService professorService;
    private static final int PAGE_SIZE = 10;

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
        Page<Professor> professoresPage = professorService.findPaginated(page, PAGE_SIZE);
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
    public ModelAndView addProfessorView(ProfessorForm professorForm) {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("title", "Registrar Professor");
        return mv;
    }

    @PostMapping(value = "/professores/registrar")
    public ModelAndView addProfessorPost(@Valid @ModelAttribute ProfessorForm professorForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return addProfessorView(professorForm);
        }
        professorService.save(professorForm);
        return new ModelAndView("redirect:/professores");
    }

    @GetMapping(value = "/professores/{id}/editar")
    public ModelAndView editProfessorView(@PathVariable("id") Long id, ProfessorForm professorForm) {
        Professor professor = professorService.findById(id);
        if (professor == null) return new ModelAndView("redirect:/professores");

        professorForm.loadFromProfessor(professor);
        ModelAndView mv = new ModelAndView("professores/edit");
        mv.addObject("title", "Editar Professor");
        mv.addObject("id", id);
        return mv;
    }

    @PostMapping(value = "/professores/{id}/editar")
    public ModelAndView editProfessorPost(@PathVariable("id") Long id, @Valid @ModelAttribute ProfessorForm professorForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return editProfessorView(id, professorForm);
        }
        professorService.update(id, professorForm);
        return new ModelAndView("redirect:/professores");
    }

    @GetMapping(value = "/professores/{id}/deletar")
    public String deletarProfessor(@PathVariable("id") Long id) {
        professorService.remove(id);
        return "redirect:/professores";
    }

    @GetMapping(value = "/professores/{id}/detalhes")
    public String detalhesProfessor(@PathVariable("id") Long id, Model model) {
        Professor professor = professorService.findById(id);
        model.addAttribute("title", String.format("Detalhes de %s", professor.getNome()));
        model.addAttribute("professor", professor);
        return "professores/details";
    }

}
