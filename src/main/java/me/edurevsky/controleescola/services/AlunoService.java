package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.exceptions.appexceptions.NotImplementedException;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import me.edurevsky.controleescola.utils.GeradorDeEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.transaction.annotation.Transactional;

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
        return alunoRepository.save(AlunoForm.convertToAluno(alunoForm));
    }

    @Transactional
    public void remove(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        alunoRepository.deleteById(id);
    }

    @Transactional
    public Aluno update(Long id, AlunoForm alunoForm) {
        Handlers.handleEntityNotFound(alunoRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        Aluno aluno = alunoRepository.getById(id);
        BeanUtils.copyProperties(alunoForm, aluno);

        aluno.setEmail(GeradorDeEmail.gerarEmailParaAluno(alunoForm.getNome()));

        return alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    @Transactional
    public Aluno addTurma(Long idAluno, Long idTurma) {
        throw new NotImplementedException();
//        Handlers.handleEntityNotFound(alunoRepository, idAluno, String.format(NOT_FOUND_MESSAGE, idAluno));
//        Handlers.handleEntityNotFound(turmaRepository, idTurma, String.format("Turma com id %d não encontrada", idTurma));
//
//        Aluno aluno = alunoRepository.findById(idAluno).get();
//        aluno.setTurma(turmaRepository.getById(idTurma));
//        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno updateCpf(Long idAluno, String cpf) {
        throw new NotImplementedException();
//        Handlers.handleEntityNotFound(alunoRepository, idAluno, String.format(NOT_FOUND_MESSAGE, idAluno));
//
//        Aluno aluno = alunoRepository.getById(idAluno);
//        aluno.setCpf(cpf);
//        return alunoRepository.save(aluno);
    }

    /**
     * Switches the Aluno attribute 'estaAtivo'
     *
     * @param id the Aluno id
     * @return the updated Aluno Object
     */
    @Transactional
    public Aluno switchEstaAtivo(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Aluno aluno = alunoRepository.getById(id);
        aluno.setEstaAtivo(!aluno.getEstaAtivo());
        return alunoRepository.save(aluno);
    }

    public List<Aluno> findByEstaAtivo(Boolean estaAtivo) {
        return alunoRepository.findByEstaAtivo(estaAtivo);
    }

}
