package me.edurevsky.controleescola.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import me.edurevsky.controleescola.services.contracts.turma.BuscaTurma;

@Service
public class TurmaService implements BuscaTurma {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public List<Turma> buscarTodas() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id).get();
    }
    
}
