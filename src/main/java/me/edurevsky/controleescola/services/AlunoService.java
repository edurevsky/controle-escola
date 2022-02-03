package me.edurevsky.controleescola.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.services.contracts.BuscaAluno;
import me.edurevsky.controleescola.services.contracts.RegistroAluno;

@Service
public class AlunoService implements RegistroAluno, BuscaAluno {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Page<Aluno> buscarTodos(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    @Override
    public Aluno registrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno buscaPorId(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow( /**TODO: Tratamento de erro */ );
    }

    @Override
    public Boolean atualizaAluno(Long id, Aluno aluno) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.save(aluno);
            return true;
        }
        return false;
    }

    @Override
    public Boolean removerAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
