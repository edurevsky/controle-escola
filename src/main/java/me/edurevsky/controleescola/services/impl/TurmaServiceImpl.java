package me.edurevsky.controleescola.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.repositories.TurmaRepository;
import me.edurevsky.controleescola.services.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma findById(Long id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Turma com id " + id + " não encontrada."));
    }
    
}
