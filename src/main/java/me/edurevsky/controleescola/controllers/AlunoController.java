package me.edurevsky.controleescola.controllers;

import javax.validation.Valid;

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
import me.edurevsky.controleescola.controllers.utils.IdTurmaObject;
import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.services.impl.AlunoServiceImpl;
import me.edurevsky.controleescola.services.impl.TurmaServiceImpl;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoServiceImpl alunoService;

    @Autowired
    private TurmaServiceImpl turmaService;

    @PostMapping
    public ResponseEntity<?> registrarAluno(@RequestBody @Valid AlunoForm alunoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(alunoService.save(alunoDTO));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(Pageable pageable) {
        Page<Aluno> alunos = alunoService.findAll(pageable);
        return ResponseEntity.ok().body(alunos);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Aluno aluno = alunoService.findById(id);
        return ResponseEntity.ok().body(aluno);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> removerAluno(@PathVariable Long id) {
        alunoService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/turma/{idTurma}")
    public ResponseEntity<?> buscarAlunosPorIdDaTurma(@PathVariable Long idTurma) {
        return ResponseEntity.ok().body(turmaService.buscarPorId(idTurma));
    }

    @PutMapping(value = "/{idAluno}/transferir")
    public ResponseEntity<?> transferirTurma(@PathVariable Long idAluno, @RequestBody IdTurmaObject idTurma) {
        alunoService.changeTurma(idAluno, idTurma.getTurma());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idAluno}/alterar-cpf")
    public ResponseEntity<?> alterarCpf(@PathVariable Long idAluno, @RequestBody CpfObject cpf) {
        alunoService.updateCpf(idAluno, cpf.getCpf());
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idAluno}/alterar-atividade")
    public ResponseEntity<?> alterarAtividade(@PathVariable Long idAluno) {
        alunoService.switchEstaAtivo(idAluno);
        return ResponseEntity.noContent().build();
    }

}
