package me.edurevsky.controleescola.web.controllers;

import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.forms.EditProfessorForm;
import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/professores")
public class ProfessorViewController {

    private final ProfessorService professorService;
    private static final int PAGE_SIZE = 10;

    @Autowired
    public ProfessorViewController(final ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ModelAndView index() {
        return paginatedProfessores(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedProfessores(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("professores/index");
        Page<Professor> professoresPage = professorService.findPaginated(page, PAGE_SIZE);
        List<Professor> professoresList = professoresPage.getContent();

        // Title
        mv.addObject("title", "Lista de Professores");

        // Pagination
        mv.addObject("currentPage", page);
        mv.addObject("totalPages", professoresPage.getTotalPages());
        mv.addObject("totalItems", professoresPage.getTotalElements());

        mv.addObject("professoresList", professoresList);
        return mv;
    }

    @GetMapping(value = "/registrar")
    public ModelAndView addProfessorView(ProfessorForm professorForm) {
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("title", "Registrar Professor");
        return mv;
    }

    @PostMapping(value = "/registrar")
    public ModelAndView addProfessorPost(@Valid @ModelAttribute ProfessorForm professorForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return addProfessorView(professorForm);
        }
        professorService.save(professorForm);
        return new ModelAndView("redirect:/professores");
    }

    @GetMapping(value = "/{id}/editar")
    public ModelAndView editProfessorView(@PathVariable("id") Long id, EditProfessorForm professorForm) {
        Professor professor = professorService.findById(id);
        if (professor == null) return new ModelAndView("redirect:/professores");

        professorForm.loadFromProfessor(professor);
        ModelAndView mv = new ModelAndView("professores/edit");
        mv.addObject("title", "Editar Professor");
        mv.addObject("id", id);
        mv.addObject("cpf", professor.getCpf());
        return mv;
    }

    @PostMapping(value = "/{id}/editar")
    public ModelAndView editProfessorPost(@PathVariable("id") Long id, @Valid @ModelAttribute EditProfessorForm professorForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return editProfessorView(id, professorForm);
        }
        professorService.update(id, professorForm);
        return new ModelAndView("redirect:/professores");
    }

    @GetMapping(value = "/{id}/deletar")
    public ModelAndView deletarProfessor(@PathVariable("id") Long id) {
        professorService.remove(id);
        return new ModelAndView("redirect:/professores");
    }

    @GetMapping(value = "/{id}/detalhes")
    public ModelAndView detalhesProfessor(@PathVariable("id") Long id) {
        Professor professor = professorService.findById(id);
        ModelAndView mv = new ModelAndView("professores/details");
        mv.addObject("title", String.format("Detalhes de %s", professor.getNome()));
        mv.addObject("professor", professor);
        return mv;
    }

}
