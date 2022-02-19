package me.edurevsky.controleescola.services;

import java.util.List;

import me.edurevsky.controleescola.entities.Turma;

public interface TurmaService {
    
    public List<Turma> findAll();

    public Turma findById(Long id);

}
