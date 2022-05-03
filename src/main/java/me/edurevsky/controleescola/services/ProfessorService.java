package me.edurevsky.controleescola.services;

import lombok.RequiredArgsConstructor;
import me.edurevsky.controleescola.entities.AppUser;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.forms.EditProfessorForm;
import me.edurevsky.controleescola.forms.ProfessorForm;
import me.edurevsky.controleescola.repositories.ProfessorRepository;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import me.edurevsky.controleescola.services.utils.CpfHandler;
import me.edurevsky.controleescola.services.utils.Handlers;
import me.edurevsky.controleescola.utils.GeradorDeEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final CpfHandler cpfHandler;
    private final AppUserService appUserService;
    private static final String NOT_FOUND_MESSAGE = "Professor com id %d não encontrado";

    @Transactional
    public Professor save(ProfessorForm professorForm) {
        cpfHandler.ifAlreadyRegistered_ThrowException(professorForm.getCpf());

        Professor professor = ProfessorForm.convertToProfessor(professorForm);
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor update(Long id, ProfessorForm professorForm) {
        Handlers.handleEntityNotFound(professorRepository, id, String.format(NOT_FOUND_MESSAGE, id));
        Professor professor = professorRepository.getById(id);

        if (!professorForm.getCpf().equals(professor.getCpf())) {
            cpfHandler.ifAlreadyRegistered_ThrowException(professorForm.getCpf());
        }
        BeanUtils.copyProperties(professorForm, professor);
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor update(Long id, EditProfessorForm professorForm) {
        Handlers.handleEntityNotFound(professorRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Professor professor = professorRepository.getById(id);
        professor.setEmail(GeradorDeEmail.gerarEmailParaProfessor(professorForm.getNome()));
        return professorRepository.save(EditProfessorForm.update(professor, professorForm));
    }

    @Transactional
    public void remove(Long id) {
        Handlers.handleEntityNotFound(professorRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Professor professor = professorRepository.getById(id);
        professor.setAllTurmasProfessorNull();
        turmaRepository.saveAll(professor.getTurmas());
        professorRepository.delete(professor);
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
    }

    public Professor findByEmail(String email) {
        return professorRepository.findByEmail(email);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Page<Professor> findPaginated(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return professorRepository.findAll(pageable);
    }
}
