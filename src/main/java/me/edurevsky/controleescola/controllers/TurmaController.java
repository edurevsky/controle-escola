package me.edurevsky.controleescola.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.services.TurmaService;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {
    
    @Autowired
    private TurmaService turmaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Turma turma = turmaService.buscarPorId(id);
        return ResponseEntity.ok().body(turma);
    }

}
