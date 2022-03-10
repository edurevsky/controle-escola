package me.edurevsky.controleescola.services;

import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.repositories.ProfessorRepository;
import me.edurevsky.controleescola.services.utils.Handlers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private static final String NOT_FOUND_MESSAGE = "Professor com id %d não encontrado";

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor save(ProfessorForm professorForm) {
        return professorRepository.save(ProfessorForm.convertToProfessor(professorForm));
    }

    public Professor update(Long id, ProfessorForm professorForm) {
        Handlers.handleEntityNotFound(professorRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        Professor professor = professorRepository.getById(id);
        BeanUtils.copyProperties(professorForm, professor);
        return professorRepository.save(professor);
    }

    public void remove(Long id) {
        Handlers.handleEntityNotFound(professorRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        professorRepository.deleteById(id);
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

}
