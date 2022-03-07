package me.edurevsky.controleescola.controllers;

import me.edurevsky.controleescola.controllers.utils.IdObject;
import me.edurevsky.controleescola.dtos.TurmaDTO;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                turmaService.findAll()
                        .stream()
                        .map(TurmaDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody TurmaForm turmaForm) {
        return ResponseEntity.ok(turmaService.save(turmaForm));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(new TurmaDTO(turma));
    }

    @PutMapping(value = "/{id}/adicionar-professor")
    public ResponseEntity<?> addProfessor(@PathVariable("id") Long idTurma, @RequestBody IdObject idProfessor) {
        return ResponseEntity.ok(turmaService.addProfessor(idTurma, idProfessor.getId()));
    }

}
