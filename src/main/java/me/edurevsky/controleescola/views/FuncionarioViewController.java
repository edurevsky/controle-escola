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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FuncionarioViewController {

    private final FuncionarioService funcionarioService;
    private final CargoService cargoService;
    private static final int pageSize = 10;

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
        Page<Funcionario> funcionariosPage = funcionarioService.findPaginated(page, pageSize);
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
    public String addFuncionarioView(FuncionarioForm funcionarioForm, Model model) {
        model.addAttribute("title", "Registrar Funcionário");
        model.addAttribute("cargosList", cargoService.findAll());
        model.addAttribute("turnos", Turno.values());
        return "funcionarios/new";
    }

    @PostMapping(value = "/funcionarios/registrar")
    public String addFuncionarioPost(@ModelAttribute FuncionarioForm funcionarioForm) {
        funcionarioService.save(funcionarioForm);
        return "redirect:/funcionarios";
    }

    @GetMapping(value = "/funcionarios/{id}/deletar")
    public String deletarFuncionario(@PathVariable Long id) {
        funcionarioService.remove(id);
        return "redirect:/funcionarios";
    }

    @GetMapping(value = "/funcionarios/{id}/editar")
    public String editarFuncionario(FuncionarioForm funcionarioForm, @PathVariable Long id, Model model) {
        model.addAttribute("title", "Editar funcionário");
        model.addAttribute("funcionario", funcionarioService.findById(id));
        model.addAttribute("turnos", Turno.values());
        model.addAttribute("cargosList", cargoService.findAll());
        return "funcionarios/edit";
    }

    @PostMapping(value = "/funcionarios/{id}/editar")
    public String editarFuncionarioPost(@ModelAttribute FuncionarioForm funcionarioForm, @PathVariable Long id) {
        funcionarioService.update(id, funcionarioForm);
        return "redirect:/funcionarios";
    }

    @GetMapping(value = "/funcionarios/{id}/detalhes")
    public String detalhesFuncionario(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioService.findById(id);
        model.addAttribute("title", String.format("Detalhes de %s", funcionario.getNome()));
        model.addAttribute("funcionario", funcionario);
        return "funcionarios/details";
    }

}
