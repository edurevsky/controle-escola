package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.forms.CargoForm;
import me.edurevsky.controleescola.repositories.CargoRepository;
import me.edurevsky.controleescola.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CargoViewController {

    private final CargoService cargoService;

    @Autowired
    public CargoViewController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping(value = "/cargos")
    public String index(Model model) {
        model.addAttribute("title", "Lista de Cargos");
        model.addAttribute("cargosList", cargoService.findAll());
        return "cargos/index";
    }

    @GetMapping(value = "/cargos/registrar")
    public String novoCargoView(CargoForm cargoForm, Model model) {
        model.addAttribute("title", "Registrar Cargo");
        return "cargos/addcargo";
    }

    @PostMapping(value = "/cargos/registrar")
    public String novoCargoPost(@ModelAttribute CargoForm cargoForm) {
        cargoService.save(cargoForm);
        return "redirect:/cargos";
    }

}
