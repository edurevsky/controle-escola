package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.forms.TurmaForm;
import me.edurevsky.controleescola.repositories.ProfessorRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
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
    
}
