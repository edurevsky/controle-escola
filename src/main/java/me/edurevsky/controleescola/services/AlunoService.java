package me.edurevsky.controleescola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.contracts.RegistroAluno;

@Service
public class AlunoService implements RegistroAluno {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno registrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

}
