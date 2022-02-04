package me.edurevsky.controleescola.services.contracts.turma;

import java.util.List;

import me.edurevsky.controleescola.entities.Turma;

public interface BuscaTurma {
    
    public List<Turma> buscarTodas(); 
    
    public Turma buscarPorId(Long id);

}
