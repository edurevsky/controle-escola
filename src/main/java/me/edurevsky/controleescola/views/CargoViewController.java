package me.edurevsky.controleescola.views;

import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.forms.CargoForm;
import me.edurevsky.controleescola.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CargoViewController {

    private final CargoService cargoService;
    private static final int pageSize = 10;

    @Autowired
    public CargoViewController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping(value = "/cargos")
    public String index(Model model) {
        return paginatedCargos(1, model);
    }

    @GetMapping(value = "/cargos/{page}")
    public String paginatedCargos(@PathVariable("page") Integer page, Model model)
    {
        Page<Cargo> cargosPage = cargoService.findPaginated(page, pageSize);
        List<Cargo> cargosList = cargosPage.getContent();

        // Title
        model.addAttribute("title", "Lista de Cargos");

        // Pagination
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", cargosPage.getTotalPages());
        model.addAttribute("totalItems", cargosPage.getTotalElements());

        model.addAttribute("cargosList", cargosList);
        return "cargos/index";
    }

    @GetMapping(value = "/cargos/registrar")
    public String novoCargoView(CargoForm cargoForm, Model model) {
        model.addAttribute("title", "Registrar Cargo");
        return "cargos/new";
    }

    @PostMapping(value = "/cargos/registrar")
    public String novoCargoPost(@ModelAttribute CargoForm cargoForm) {
        cargoService.save(cargoForm);
        return "redirect:/cargos";
    }

    @GetMapping(value = "/cargos/{id}/deletar")
    public String deletarCargo(@PathVariable Long id) {
        cargoService.remove(id);
        return "redirect:/cargos";
    }

    @GetMapping(value = "/cargos/{id}/detalhes")
    public String detalhesCargo(@PathVariable Long id, Model model) {
        Cargo cargo = cargoService.findById(id);
        model.addAttribute("title", String.format("Detalhes do cargo %s", cargo.getCargo()));
        model.addAttribute("cargo", cargo);
        return "cargos/details";
    }

    @GetMapping(value = "/cargos/{id}/editar")
    public String editarCargo(@ModelAttribute CargoForm cargoForm, @PathVariable Long id, Model model) {
        model.addAttribute("cargo", cargoService.findById(id));
        model.addAttribute("title", "Editar cargo");
        return "cargos/edit";
    }

    @PostMapping(value = "/cargos/{id}/editar")
    public String editarCargoPost(@ModelAttribute CargoForm cargoForm, @PathVariable Long id) {
        cargoService.update(id, cargoForm);
        return "redirect:/cargos";
    }

}
