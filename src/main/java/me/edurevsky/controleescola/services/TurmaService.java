package me.edurevsky.controleescola.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.repositories.ProfessorRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.repositories.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private static final String NOT_FOUND_MESSAGE = "Turma com id %d não encontrada";

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, ProfessorRepository professorRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
    }

    public Turma save(TurmaForm turmaForm) {
        return turmaRepository.save(TurmaForm.convertToTurma(turmaForm));
    }

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

    public Turma addProfessor(Long idTurma, Long idProfessor) {
        Handlers.handleEntityNotFound(turmaRepository, idTurma, String.format(NOT_FOUND_MESSAGE, idTurma));

        Turma turma = turmaRepository.getById(idTurma);
        turma.setProfessor(professorRepository.getById(idProfessor));
        return turmaRepository.save(turma);
    }

    public List<Aluno> findByIdGetAlunos(Long id) {
        Handlers.handleEntityNotFound(turmaRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Turma turma = turmaRepository.getById(id);
        return turma.getAlunos();
    }
    
}
