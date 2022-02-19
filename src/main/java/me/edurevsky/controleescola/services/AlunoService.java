package me.edurevsky.controleescola.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.forms.AlunoForm;

public interface AlunoService {
    
    public Aluno save(AlunoForm alunoForm);

    public void remove(Long id);

    public Page<Aluno> findAll(Pageable pageable);

    public Aluno findById(Long id);

    public void changeTurma(Long idAluno, Long idTurma);

    public void switchEstaAtivo(Long id);

}
