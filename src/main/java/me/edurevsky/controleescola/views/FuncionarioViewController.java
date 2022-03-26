package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.forms.FuncionarioForm;
import me.edurevsky.controleescola.services.CargoService;
import me.edurevsky.controleescola.services.FuncionarioService;
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
public class FuncionarioViewController {

    private final FuncionarioService funcionarioService;
    private final CargoService cargoService;
    private static final int PAGE_SIZE = 10;

    @Autowired
    public FuncionarioViewController(FuncionarioService funcionarioService, CargoService cargoService) {
        this.funcionarioService = funcionarioService;
        this.cargoService = cargoService;
    }

    @GetMapping(value = "/funcionarios")
    public String index(Model model) {
        return paginatedFuncionarios(1, model);
    }

    @GetMapping(value = "/funcionarios/{page}")
    public String paginatedFuncionarios(@PathVariable("page") Integer page, Model model) {
        Page<Funcionario> funcionariosPage = funcionarioService.findPaginated(page, PAGE_SIZE);
        List<Funcionario> funcionariosList = funcionariosPage.getContent();

        // Title
        model.addAttribute("title", "Lista de Funcionários");

        // Pagination
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", funcionariosPage.getTotalPages());
        model.addAttribute("totalItems", funcionariosPage.getTotalElements());

        model.addAttribute("funcionariosList", funcionariosList);
        return "funcionarios/index";
    }

    @GetMapping(value = "/funcionarios/registrar")
    public ModelAndView addFuncionarioView(FuncionarioForm funcionarioForm) {
        ModelAndView mv = new ModelAndView("funcionarios/new");
        mv.addObject("title", "Registrar Funcionário");
        mv.addObject("cargosList", cargoService.findAll());
        mv.addObject("turnos", Turno.values());
        return mv;
    }

    @PostMapping(value = "/funcionarios/registrar")
    public ModelAndView addFuncionarioPost(@Valid @ModelAttribute FuncionarioForm funcionarioForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return addFuncionarioView(funcionarioForm);
        }
        funcionarioService.save(funcionarioForm);
        return new ModelAndView("redirect:/funcionarios");
    }

    @GetMapping(value = "/funcionarios/{id}/deletar")
    public String deletarFuncionario(@PathVariable Long id) {
        funcionarioService.remove(id);
        return "redirect:/funcionarios";
    }

    @GetMapping(value = "/funcionarios/{id}/editar")
    public ModelAndView editarFuncionario(FuncionarioForm funcionarioForm, @PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findById(id);
        if (funcionario == null) return new ModelAndView("redirect:/funcionarios");

        funcionarioForm.loadFromFuncionario(funcionario);
        ModelAndView mv = new ModelAndView("funcionarios/edit");
        mv.addObject("title", "Editar funcionário");
        mv.addObject("id", id);
        mv.addObject("turnos", Turno.values());
        mv.addObject("cargosList", cargoService.findAll());
        return mv;
    }

    @PostMapping(value = "/funcionarios/{id}/editar")
    public ModelAndView editarFuncionarioPost(@PathVariable("id") Long id, @Valid @ModelAttribute FuncionarioForm funcionarioForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return editarFuncionario(funcionarioForm, id);
        }
        funcionarioService.update(id, funcionarioForm);
        return new ModelAndView("redirect:/funcionarios");
    }

    @GetMapping(value = "/funcionarios/{id}/detalhes")
    public String detalhesFuncionario(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioService.findById(id);
        model.addAttribute("title", String.format("Detalhes de %s", funcionario.getNome()));
        model.addAttribute("funcionario", funcionario);
        return "funcionarios/details";
    }

}
