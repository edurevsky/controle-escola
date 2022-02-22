package me.edurevsky.controleescola.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.edurevsky.controleescola.controllers.utils.CpfObject;
import me.edurevsky.controleescola.controllers.utils.SalarioObject;
import me.edurevsky.controleescola.forms.FuncionarioForm;
import me.edurevsky.controleescola.services.impl.CargoServiceImpl;
import me.edurevsky.controleescola.services.impl.FuncionarioServiceImpl;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioServiceImpl funcionarioService;

    @Autowired
    private CargoServiceImpl cargoService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody FuncionarioForm funcionarioForm) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(funcionarioService.save(funcionarioForm));
    }

    @GetMapping(params = "cargo")
    public ResponseEntity<?> findByCargoName(@RequestParam String cargo) {
        return ResponseEntity.ok().body(cargoService.findByName(cargo));
    }

    @GetMapping(params = "id_cargo")
    public ResponseEntity<?> findByCargoId(@RequestParam(value = "id_cargo") Long idCargo) {
        return ResponseEntity.ok().body(cargoService.findById(idCargo));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        funcionarioService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(funcionarioService.findById(id));
    }

    @PutMapping(value = "/{id}/alterar-salario")
    public ResponseEntity<?> updateSalary(@PathVariable Long id, @RequestBody SalarioObject salario) {
        funcionarioService.updateSalary(id, salario.getSalario());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/alterar-cpf")
    public ResponseEntity<?> updateCpf(@PathVariable Long id, @RequestBody CpfObject cpf) {
        funcionarioService.updateCpf(id, cpf.getCpf());
        return ResponseEntity.noContent().build();
    }

}
