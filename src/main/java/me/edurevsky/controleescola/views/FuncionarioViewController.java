package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.forms.FuncionarioForm;
import me.edurevsky.controleescola.services.CargoService;
import me.edurevsky.controleescola.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FuncionarioViewController {

    private final FuncionarioService funcionarioService;
    private final CargoService cargoService;

    @Autowired
    public FuncionarioViewController(FuncionarioService funcionarioService, CargoService cargoService) {
        this.funcionarioService = funcionarioService;
        this.cargoService = cargoService;
    }

    @GetMapping(value = "/funcionarios")
    public String index(Model model) {
        model.addAttribute("title", "Lista de Funcionários");
        model.addAttribute("funcionariosList", funcionarioService.findAll());
        return "funcionarios/index";
    }

    @GetMapping(value = "/funcionarios/registrar")
    public String addFuncionarioView(FuncionarioForm funcionarioForm, Model model) {
        model.addAttribute("title", "Registrar Funcionário");
        model.addAttribute("cargosList", cargoService.findAll());
        model.addAttribute("turnos", Turno.values());
        return "funcionarios/addfuncionario";
    }

    @PostMapping(value = "/funcionarios/registrar")
    public String addFuncionarioPost(@ModelAttribute FuncionarioForm funcionarioForm) {
        funcionarioService.save(funcionarioForm);
        return "redirect:/funcionarios";
    }

}
