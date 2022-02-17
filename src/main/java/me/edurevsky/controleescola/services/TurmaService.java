package me.edurevsky.controleescola.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.repositories.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> buscarTodas() {
        return turmaRepository.findAll();
    }

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Turma com id " + id + " não encontrada."));
    }
    
}
