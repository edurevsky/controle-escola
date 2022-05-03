package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.exceptions.appexceptions.NotImplementedException;
import me.edurevsky.controleescola.forms.AlterarTurmaForm;
import me.edurevsky.controleescola.forms.EditAlunoForm;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import me.edurevsky.controleescola.services.utils.CpfHandler;
import me.edurevsky.controleescola.utils.GeradorDeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private static final String NOT_FOUND_MESSAGE = "Aluno com id %d não encontrado";

    @Transactional
    public Aluno save(AlunoForm alunoForm) {
        cpfHandler.ifAlreadyRegistered_ThrowException(alunoForm.getCpf());
        return alunoRepository.save(AlunoForm.convertToAluno(alunoForm));
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
        aluno.setEmail(GeradorDeEmail.gerarEmailParaAluno(alunoForm.getNome()));
        return alunoRepository.save(EditAlunoForm.update(aluno, alunoForm));
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
        Handlers.handleEntityNotFound(alunoRepository, idAluno, String.format(NOT_FOUND_MESSAGE, idAluno));
        Handlers.handleEntityNotFound(turmaRepository, idTurma, String.format("Turma com id %d não encontrada", idTurma));

        Aluno aluno = alunoRepository.getById(idAluno);
        aluno.setTurma(turmaRepository.getById(idTurma));
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno addTurma(Long idAluno, AlterarTurmaForm turmaForm) {
        Handlers.handleEntityNotFound(alunoRepository, idAluno, String.format(NOT_FOUND_MESSAGE, idAluno));

        Aluno aluno = alunoRepository.getById(idAluno);
        aluno.setTurma(turmaForm.getTurma());
        return alunoRepository.save(aluno);
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
