package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.exceptions.appexceptions.NotImplementedException;
import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.repositories.ProfessorRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private static final String NOT_FOUND_MESSAGE = "Turma com id %d não encontrada";

    @Autowired
    public TurmaService(
            TurmaRepository turmaRepository,
            ProfessorRepository professorRepository,
            AlunoRepository alunoRepository
    ) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public Turma save(TurmaForm turmaForm) {
        return turmaRepository.save(TurmaForm.convertToTurma(turmaForm));
    }

    @Transactional
    public Turma update(Long id, TurmaForm turmaForm) {
        Handlers.handleEntityNotFound(turmaRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        Turma turma = turmaRepository.getById(id);
        BeanUtils.copyProperties(turmaForm, turma);
        return turmaRepository.save(turma);
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma findById(Long id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    @Transactional
    public Turma addProfessor(Long idTurma, Long idProfessor) {
        throw new NotImplementedException();
    }

    public List<Aluno> findByIdGetAlunos(Long id) {
        Handlers.handleEntityNotFound(turmaRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Turma turma = turmaRepository.getById(id);
        return turma.getAlunos();
    }

    /**
     * If the id exists, deletes de turma and
     * sets all the alunos turma to null
     * @param id The turma id
     */
    @Transactional
    public void delete(Long id) {
        Handlers.handleEntityNotFound(turmaRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Turma turma = turmaRepository.getById(id);
        turma.setAllAlunosTurmaNull();
        alunoRepository.saveAll(turma.getAlunos());
        turmaRepository.delete(turma);
    }

    public Page<Turma> findPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return turmaRepository.findAll(pageable);
    }
    
}
