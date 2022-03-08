package me.edurevsky.controleescola.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.dtos.TurmaDTO;
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

    public List<TurmaDTO> findAll() {
        return turmaRepository.findAll().stream().map(TurmaDTO::new).collect(Collectors.toList());
    }

    public TurmaDTO findById(Long id) {
        Turma turma = turmaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MESSAGE, id)));
        return new TurmaDTO(turma);
    }

    public Turma addProfessor(Long idTurma, Long idProfessor) {
        Handlers.handleEntityNotFound(turmaRepository, idTurma, String.format(NOT_FOUND_MESSAGE, idTurma));

        Turma turma = turmaRepository.getById(idTurma);
        turma.setProfessor(professorRepository.getById(idProfessor));
        return turmaRepository.save(turma);
    }

    public List<AlunoDTO> findByIdGetAlunos(Long id) {
        Handlers.handleEntityNotFound(turmaRepository, id, String.format(NOT_FOUND_MESSAGE, id));

        Turma turma = turmaRepository.getById(id);
        return turma.getAlunos().stream().map(AlunoDTO::new).collect(Collectors.toList());
    }
    
}
