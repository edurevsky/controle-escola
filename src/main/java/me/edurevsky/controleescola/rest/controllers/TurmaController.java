package me.edurevsky.controleescola.rest.controllers;

import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(turmaService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TurmaForm turmaForm) {
        return ResponseEntity.ok(turmaService.save(turmaForm));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(turmaService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TurmaForm turmaForm) {
        return ResponseEntity.ok(turmaService.update(id, turmaForm));
    }

    @GetMapping(value = "/{id}/alunos")
    public ResponseEntity<?> findByIdGetAlunos(@PathVariable("id") Long id) {
        return ResponseEntity.ok(turmaService.findByIdGetAlunos(id));
    }

    @PutMapping(value = "/{idTurma}/atualizar-professor/{idProfessor}")
    public ResponseEntity<?> addProfessor(@PathVariable("idTurma") Long idTurma, @PathVariable("idProfessor") Long idProfessor) {
        return ResponseEntity.ok(turmaService.addProfessor(idTurma, idProfessor));
    }

}
