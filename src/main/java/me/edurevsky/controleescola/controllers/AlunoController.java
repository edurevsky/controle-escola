package me.edurevsky.controleescola.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.edurevsky.controleescola.controllers.utils.IdTurmaObject;
import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<?> registrarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno alunoRegistrado = alunoService.registrarAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoRegistrado);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(Pageable pageable) {
        Page<Aluno> alunos = alunoService.buscarTodos(pageable);
        return ResponseEntity.ok().body(alunos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Aluno aluno = alunoService.buscaPorId(id);
        return ResponseEntity.ok().body(aluno);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> removerAluno(@PathVariable Long id) {
        boolean wasDeleted = alunoService.removerAluno(id);
        if (!wasDeleted) {
            return ResponseEntity.notFound().build();  
        } 
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idAluno}/transferir")
    public ResponseEntity<?> transferirTurma(@PathVariable Long idAluno, @RequestBody IdTurmaObject idTurma) {
        boolean foiTransferido = alunoService.transferirTurma(idAluno, idTurma.getIdTurma());
        if (!foiTransferido) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    // @PutMapping(value = "/{id}")
    // public ResponseEntity<?> atualizarAluno(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
    //     boolean wasUpdated = alunoService.atualizaAluno(id, alunoDTO);
    //     if (!wasUpdated) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok().build();
    // }

}
