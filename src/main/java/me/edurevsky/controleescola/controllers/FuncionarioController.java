package me.edurevsky.controleescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.edurevsky.controleescola.controllers.utils.SalarioObject;
import me.edurevsky.controleescola.dtos.FuncionarioDTO;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.services.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> registrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = funcionarioService.registrarFuncionario(funcionarioDTO);
        return ResponseEntity.ok().body(funcionario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> removerFuncionario(@PathVariable Long id) {
        boolean wasDeleted = funcionarioService.removerFuncionario(id);
        if (!wasDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> alterarSalario(@PathVariable Long id, @RequestBody SalarioObject salario) {
        boolean foiAlterado = funcionarioService.alterarSalario(id, salario.getSalario());
        if (!foiAlterado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
