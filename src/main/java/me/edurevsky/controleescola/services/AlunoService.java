package me.edurevsky.controleescola.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.utils.Handlers;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private static final String NOT_FOUND_MESSAGE = "Aluno com id %d não encontrado";

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    public Aluno save(AlunoForm alunoForm) {
        Aluno aluno = AlunoForm.convertToAluno(alunoForm);
        aluno.setTurma(turmaRepository.getById(alunoForm.getTurma()));
        return alunoRepository.save(aluno);
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        alunoRepository.deleteById(id);
    }

    public Page<AlunoDTO> findAll(Pageable pageable) {
        Page<Aluno> alunos = alunoRepository.findAll(pageable);
        int totalElements = alunos.getNumberOfElements();
        return new PageImpl<AlunoDTO>(alunos.getContent().stream().map(AlunoDTO::new).collect(Collectors.toList()), pageable, totalElements);
    }

    public AlunoDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
        return new AlunoDTO(aluno);
    }

    public void changeTurma(Long idAluno, Long idTurma) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, String.format(NOT_FOUND_MESSAGE, idAluno));
        this.handleTurmaNotFound(idTurma);

        Aluno alunoEmTransferencia = alunoRepository.findById(idAluno).get();
        alunoEmTransferencia.setTurma(turmaRepository.getById(idTurma));
        alunoRepository.save(alunoEmTransferencia);
    }

    public void updateCpf(Long idAluno, String cpf) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, String.format(NOT_FOUND_MESSAGE, idAluno));

        Aluno aluno = alunoRepository.findById(idAluno).get();
        aluno.setCpf(cpf);
        alunoRepository.save(aluno);
    }

    public void switchEstaAtivo(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Aluno aluno = alunoRepository.findById(id).get();
        aluno.setEstaAtivo(!aluno.getEstaAtivo());
        alunoRepository.save(aluno);
    }

    private void handleTurmaNotFound(Long idTurma) {
        if (!turmaRepository.existsById(idTurma)) {
            throw new EntityNotFoundException("Turma com id " + idTurma + " não encontrada.");
        }
    }

    public List<Aluno> findByEstaAtivo(Boolean estaAtivo) {
        return alunoRepository.findByEstaAtivo(estaAtivo);
    }

}
