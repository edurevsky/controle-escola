package me.edurevsky.controleescola.controllers;

import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(professorService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProfessorForm professorForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professorForm));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(professorService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        professorService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProfessorForm professorForm) {
        return ResponseEntity.ok(professorService.update(id, professorForm));
    }

}
