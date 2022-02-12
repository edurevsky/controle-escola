package me.edurevsky.controleescola.controllers;

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
import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.services.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> registrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(funcionarioService.registrarFuncionario(funcionarioDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> removerFuncionario(@PathVariable Long id) {
        funcionarioService.removerFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(funcionarioService.buscarPorId(id));
    }

    @PutMapping(value = "/{id}/alterar-salario")
    public ResponseEntity<?> alterarSalario(@PathVariable Long id, @RequestBody SalarioObject salario) {
        funcionarioService.alterarSalario(id, salario.getSalario());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/alterar-cpf")
    public ResponseEntity<?> alterarCpf(@PathVariable Long id, @RequestBody CpfObject cpf) {
        funcionarioService.alterarCpf(id, cpf.getCpf());
        return ResponseEntity.noContent().build();
    }

}
