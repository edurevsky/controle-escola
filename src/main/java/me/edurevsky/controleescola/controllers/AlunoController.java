package me.edurevsky.controleescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<?> buscarTodos(Pageable pageable) {
        Page<Aluno> alunos = alunoService.buscarTodos(pageable);
        return ResponseEntity.ok().body(alunos);
    }

}
