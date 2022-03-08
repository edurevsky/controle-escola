package me.edurevsky.controleescola.controllers;

import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.entities.Funcionario;
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
import me.edurevsky.controleescola.services.CargoService;
import me.edurevsky.controleescola.services.FuncionarioService;

@RestController
@RequestMapping(value = "api/v1/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody FuncionarioForm funcionarioForm) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(funcionarioService.save(funcionarioForm));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        funcionarioService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok().body(new FuncionarioDTO(funcionario));
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
