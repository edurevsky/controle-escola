package me.edurevsky.controleescola.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.dtos.AlunoDTO;
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

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaService turmaService;

    public Aluno save(AlunoForm alunoForm) {
        Aluno aluno = AlunoForm.convertToAluno(alunoForm);
        aluno.setTurma(turmaService.findById(alunoForm.getTurma()));
        return alunoRepository.save(aluno);
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, "Aluno com id " + id + " não encontrado");

        alunoRepository.deleteById(id);
    }

    public Page<AlunoDTO> findAll(Pageable pageable) {
        Page<Aluno> alunos = alunoRepository.findAll(pageable);
        int totalElements = alunos.getNumberOfElements();
        return new PageImpl<AlunoDTO>(alunos.getContent().stream().map(AlunoDTO::new).collect(Collectors.toList()), pageable, totalElements);
    }

    public Aluno findById(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Aluno com id " + id + " não encontrado."));
    }

    public void changeTurma(Long idAluno, Long idTurma) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, "Aluno com id " + idAluno + " não encontrado.");
        this.handleTurmaNotFound(idTurma);

        Aluno alunoEmTransferencia = alunoRepository.findById(idAluno).get();
        alunoEmTransferencia.setTurma(turmaService.findById(idTurma));
        alunoRepository.save(alunoEmTransferencia);
    }

    public void updateCpf(Long idAluno, String cpf) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, "Aluno com id " + idAluno + " não encontrado.");

        Aluno aluno = alunoRepository.findById(idAluno).get();
        aluno.setCpf(cpf);
        alunoRepository.save(aluno);
    }

    public void switchEstaAtivo(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, "Aluno com id " + id + " não encontrado.");

        Aluno aluno = alunoRepository.findById(id).get();
        aluno.setEstaAtivo(!aluno.getEstaAtivo());
        alunoRepository.save(aluno);
    }

    private void handleTurmaNotFound(Long idTurma) {
        if (turmaService.findById(idTurma) == null) {
            throw new EntityNotFoundException("Turma com id " + idTurma + " não encontrada.");
        }
    }

    public List<Aluno> findByEstaAtivo(Boolean estaAtivo) {
        return alunoRepository.findByEstaAtivo(estaAtivo);
    }

}
