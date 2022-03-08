package me.edurevsky.controleescola.controllers;

import me.edurevsky.controleescola.controllers.utils.IdObject;
import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.dtos.TurmaDTO;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        // Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turmaService.findById(id));
    }

    @GetMapping(value = "/{id}/alunos")
    public ResponseEntity<?> findByIdGetAlunos(@PathVariable("id") Long id) {
        return ResponseEntity.ok(turmaService.findByIdGetAlunos(id));
    }

    @PutMapping(value = "/{idTurma}/adicionar-professor")
    public ResponseEntity<?> addProfessor(@PathVariable("idTurma") Long idTurma, @RequestBody IdObject idProfessor) {
        return ResponseEntity.ok(turmaService.addProfessor(idTurma, idProfessor.getId()));
    }

}
