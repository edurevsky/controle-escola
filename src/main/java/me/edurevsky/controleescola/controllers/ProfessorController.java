package me.edurevsky.controleescola.controllers;

import me.edurevsky.controleescola.controllers.utils.IdObject;
import me.edurevsky.controleescola.dtos.ProfessorDTO;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                professorService.findAll()
                        .stream()
                        .map(ProfessorDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProfessorForm professorForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professorForm));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Professor professor = professorService.findById(id);
        return ResponseEntity.ok(new ProfessorDTO(professor));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        professorService.remove(id);
        return ResponseEntity.noContent().build();
    }

}
