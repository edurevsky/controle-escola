package me.edurevsky.controleescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.edurevsky.controleescola.services.CargoService;

@RestController
@RequestMapping(value = "/cargos")
public class CargoController {
    
    @Autowired
    private CargoService cargoService;

    @GetMapping(params = "nome")
    public ResponseEntity<?> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok().body(cargoService.buscarPorNomeDoCargo(nome));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(cargoService.buscarPorId(id));
    }

    @GetMapping(value = "/{id}/funcionarios")
    public ResponseEntity<?> buscarPorIdApenasFuncionarios(@PathVariable Long id) {
        return ResponseEntity.ok().body(cargoService.buscarPorId(id).getFuncionarios());
    }

}
