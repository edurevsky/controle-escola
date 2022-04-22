package me.edurevsky.controleescola.services;

import me.edurevsky.controleescola.entities.AvisosTurma;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.entities.UserDetailsImpl;
import me.edurevsky.controleescola.forms.AvisosTurmaForm;
import me.edurevsky.controleescola.repositories.AvisosTurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvisosTurmaService {

    @Autowired
    private AvisosTurmaRepository avisosTurmaRepository;

    @Autowired
    private TurmaService turmaService;

    public AvisosTurma save(AvisosTurma avisosTurma) {
        return avisosTurmaRepository.save(avisosTurma);
    }

    public AvisosTurma save(AvisosTurmaForm avisosTurmaForm, Long idTurma, UserDetailsImpl user) {
        AvisosTurma avisosTurma = new AvisosTurma();
        avisosTurma.setMensagem(avisosTurmaForm.getMensagem());
        avisosTurma.setTurma(turmaService.findById(idTurma));
        avisosTurma.setNomeUsuario(user.getUser().getCompleteName());
        avisosTurma.setData(LocalDateTime.now());
        return avisosTurmaRepository.save(avisosTurma);
    }

    public List<AvisosTurma> findByTurma(Turma turma) {
        return avisosTurmaRepository.findByTurma(turma);
    }

    public List<AvisosTurma> findByTurmaSorted(Turma turma) {
        List<AvisosTurma> avisos = avisosTurmaRepository.findByTurma(turma);
        avisos.sort((o1, o2) -> o2.getData().compareTo(o1.getData()));
        return avisos;
    }

    public AvisosTurma findLastByTurma(Turma turma) {
        List<AvisosTurma> avisos = avisosTurmaRepository.findByTurma(turma);
        if (avisos.isEmpty()) {
            return null;
        }

        avisos = avisos.stream().sorted(Comparator.comparing(AvisosTurma::getData)).collect(Collectors.toList());
        Optional<AvisosTurma> retVal = Optional.ofNullable(avisos.get(avisos.size() - 1));
        return retVal.orElse(null);
    }
}
