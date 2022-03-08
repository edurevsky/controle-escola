package me.edurevsky.controleescola.controllers;

import me.edurevsky.controleescola.forms.CargoForm;
import me.edurevsky.controleescola.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/cargos")
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CargoForm cargoForm) {
        return ResponseEntity.ok(cargoService.save(cargoForm));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cargoService.findById(id));
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(cargoService.findByName(nome));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        cargoService.remove(id);
        return ResponseEntity.noContent().build();
    }

}
