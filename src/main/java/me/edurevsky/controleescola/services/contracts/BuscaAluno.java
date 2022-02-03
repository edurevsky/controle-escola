package me.edurevsky.controleescola.services.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.edurevsky.controleescola.entities.Aluno;

public interface BuscaAluno {
    
    public Aluno buscaPorId(Long id);

    public Page<Aluno> buscarTodos(Pageable pageable);

}
