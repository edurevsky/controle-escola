package me.edurevsky.controleescola.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.AlterarCpfService;
import me.edurevsky.controleescola.services.AlunoService;
import me.edurevsky.controleescola.services.utils.Handlers;

@Service
public class AlunoServiceImpl implements AlunoService, AlterarCpfService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaServiceImpl turmaService;
    
    @Override
    public Aluno save(AlunoForm alunoDTO) {
        Aluno aluno = AlunoForm.convertToAluno(alunoDTO);
        aluno.setTurma(turmaService.findById(alunoDTO.getTurma()));
        return alunoRepository.save(aluno);
    }
    
    @Override
    public void remove(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, "Aluno com id " + id + " não encontrado");

        alunoRepository.deleteById(id);
    }

    @Override
    public Page<Aluno> findAll(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    @Override
    public Aluno findById(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Aluno com id " + id + " não encontrado."));
    }

    @Override
    public void changeTurma(Long idAluno, Long idTurma) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, "Aluno com id " + idAluno + " não encontrado.");
        this.handleTurmaNotFound(idTurma);

        Aluno alunoEmTransferencia = alunoRepository.findById(idAluno).get();
        alunoEmTransferencia.setTurma(turmaService.findById(idTurma));
        alunoRepository.save(alunoEmTransferencia);
    }

    @Override
    public void updateCpf(Long idAluno, String cpf) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, "Aluno com id " + idAluno + " não encontrado.");

        Aluno aluno = alunoRepository.findById(idAluno).get();
        aluno.setCpf(cpf);
        alunoRepository.save(aluno);
    }

    @Override
    public void switchEstaAtivo(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, "Aluno com id " + id + " não encontrado.");

        Aluno aluno = alunoRepository.findById(id).get();
        if (aluno.getEstaAtivo()) {
            aluno.setEstaAtivo(false);
        } else {
            aluno.setEstaAtivo(true);
        }
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
