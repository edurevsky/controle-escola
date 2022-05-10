package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.exceptions.appexceptions.NotImplementedException;
import me.edurevsky.controleescola.forms.AlterarTurmaForm;
import me.edurevsky.controleescola.forms.EditAlunoForm;
import me.edurevsky.controleescola.mappers.AlunoMapper;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import me.edurevsky.controleescola.services.utils.CpfHandler;
import me.edurevsky.controleescola.utils.GeradorDeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.AlunoForm;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final CpfHandler cpfHandler;
    private final AlunoMapper alunoMapper;
    private static final String NOT_FOUND_MESSAGE = "Aluno com id %d não encontrado";

    @Transactional
    public Aluno save(AlunoForm alunoForm) {
        cpfHandler.ifAlreadyRegistered_ThrowException(alunoForm.getCpf());
        // return alunoRepository.save(AlunoForm.convertToAluno(alunoForm));
        return alunoRepository.save(alunoMapper.convertToAluno(alunoForm));
    }

    @Transactional
    public void remove(Long id) {
        Handlers.handleEntityNotFound(alunoRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        alunoRepository.deleteById(id);
    }

    @Transactional
    public Aluno update(Long id, EditAlunoForm alunoForm) {
        Handlers.handleEntityNotFound(alunoRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Aluno aluno = alunoRepository.getById(id);
        return alunoRepository.save(alunoMapper.update(aluno, alunoForm));
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    @Transactional
    public void addTurma(Long idAluno, AlterarTurmaForm turmaForm) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, String.format(NOT_FOUND_MESSAGE, idAluno));
        alunoRepository.updateTurma(idAluno, turmaForm.getTurma());
    }

    @Transactional
    public Aluno updateCpf(Long idAluno, String cpf) {
        throw new NotImplementedException();
    }

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

    public Page<Aluno> findPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return alunoRepository.findAll(pageable);
    }
}
