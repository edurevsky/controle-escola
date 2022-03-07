package me.edurevsky.controleescola.services;

import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.repositories.ProfessorRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final TurmaService turmaService;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, TurmaService turmaService) {
        this.professorRepository = professorRepository;
        this.turmaService = turmaService;
    }

    public Professor save(ProfessorForm professorForm) {
        return professorRepository.save(ProfessorForm.convertToProfessor(professorForm));
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(professorRepository, id, "Professor com id " + id + " não encontrado");
        professorRepository.deleteById(id);
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor com id " + id + " não encontrado"));
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor addTurma(Long idProfessor, Long idTurma) {
        Handlers.handleEntityNotFound(professorRepository, idProfessor, "Professor com id " + idProfessor + " não encontrado");

        Professor professor = professorRepository.getById(idProfessor);
        Turma turma = turmaService.findById(idTurma);
        turma.setProfessor(professor);
        turmaService.save(turma);
        return professorRepository.save(professor);
    }

}
