package me.edurevsky.controleescola.controllers;

import javax.validation.Valid;

import me.edurevsky.controleescola.dtos.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.edurevsky.controleescola.controllers.utils.CpfObject;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.services.AlunoService;

@RestController
@RequestMapping(value = "api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AlunoForm alunoForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoForm));
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(alunoService.findAll(pageable));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(alunoService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        alunoService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idAluno}/atualizar-turma/{idTurma}")
    public ResponseEntity<?> changeTurma(@PathVariable("idAluno") Long idAluno, @PathVariable("idTurma") Long idTurma) {
        return ResponseEntity.ok(alunoService.addTurma(idAluno, idTurma));
    }

    @PutMapping(value = "/{id}/atualizar-cpf")
    public ResponseEntity<?> updateCpf(@PathVariable Long id, @RequestBody @Valid CpfObject cpf) {
        alunoService.updateCpf(id, cpf.getCpf());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/atualizar-status")
    public ResponseEntity<?> switchEstaAtivo(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.switchEstaAtivo(id));
    }

}
