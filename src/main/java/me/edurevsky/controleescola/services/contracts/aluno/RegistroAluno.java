package me.edurevsky.controleescola.services.contracts.aluno;

import me.edurevsky.controleescola.dtos.AlunoDTO;
import me.edurevsky.controleescola.entities.Aluno;

public interface RegistroAluno {
    
    public Aluno registrarAluno(AlunoDTO aluno);

    public Boolean atualizaAluno(Long id, Aluno aluno);

    public Boolean removerAluno(Long id);

}
