package me.edurevsky.controleescola.web.controllers;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.forms.CargoForm;
import me.edurevsky.controleescola.services.CargoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;
    private static final int PAGE_SIZE = 10;

    @GetMapping
    public ModelAndView index() {
        return paginatedCargos(1);
    }

    @GetMapping(value = "/{page}")
    public ModelAndView paginatedCargos(@PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("cargos/index");
        Page<Cargo> cargosPage = cargoService.findPaginated(page, PAGE_SIZE);
        List<Cargo> cargosList = cargosPage.getContent();

        mv.addObject("title", "Lista de Cargos");

        mv.addObject("currentPage", page);
        mv.addObject("totalPages", cargosPage.getTotalPages());
        mv.addObject("totalItems", cargosPage.getTotalElements());

        mv.addObject("cargosList", cargosList);
        return mv;
    }

    @GetMapping(value = "/registrar")
    public ModelAndView newCargoGet(CargoForm cargoForm) {
        ModelAndView mv = new ModelAndView("cargos/new");
        mv.addObject("title", "Registrar Cargo");
        return mv;
    }

    @PostMapping(value = "/registrar")
    public ModelAndView newCargoPost(@ModelAttribute CargoForm cargoForm) {
        cargoService.save(cargoForm);
        return redirect();
    }

    @GetMapping(value = "/{id}/deletar")
    public ModelAndView deleteCargo(@PathVariable Long id) {
        cargoService.remove(id);
        return redirect();
    }

    @GetMapping(value = "/{id}/detalhes")
    public ModelAndView detailsCargo(@PathVariable Long id) {
        Cargo cargo = cargoService.findById(id);
        ModelAndView mv = new ModelAndView("cargos/details");
        mv.addObject("title", String.format("Detalhes do cargo %s", cargo.getCargo()));
        mv.addObject("cargo", cargo);
        return mv;
    }

    @GetMapping(value = "/{id}/editar")
    public ModelAndView editCargoGet(@ModelAttribute CargoForm cargoForm, @PathVariable Long id) {
        Cargo cargo = cargoService.findById(id);
        if (Objects.isNull(cargo)) {
            return redirect();
        }
        cargoForm.loadFromCargo(cargo);
        ModelAndView mv = new ModelAndView("cargos/edit");
        mv.addObject("id", id);
        mv.addObject("title", "Editar cargo");
        return mv;
    }

    @PostMapping(value = "/{id}/editar")
    public ModelAndView editCargoPost(@ModelAttribute CargoForm cargoForm, @PathVariable Long id) {
        cargoService.update(id, cargoForm);
        return redirect();
    }

    private ModelAndView redirect() {
        return new ModelAndView("redirect:/cargos");
    }
}
