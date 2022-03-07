package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, ProfessorRepository professorRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
    }

    public Turma save(Turma turma) {
        return turmaRepository.save(turma);
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma findById(Long id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Turma com id " + id + " não encontrada."));
    }

    public void addProfessor(Long idTurma, Long idProfessor) {
        Handlers.handleEntityNotFound(turmaRepository, idTurma, "Turma com id " + idTurma + " não encontrada");

        Turma turma = turmaRepository.getById(idTurma);
        turma.setProfessor(professorRepository.getById(idProfessor));
        turmaRepository.save(turma);
    }
    
}
