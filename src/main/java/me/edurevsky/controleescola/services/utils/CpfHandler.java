package me.edurevsky.controleescola.services.utils;

import me.edurevsky.controleescola.exceptions.appexceptions.CpfJaExisteException;
import me.edurevsky.controleescola.repositories.AlunoRepository;
import me.edurevsky.controleescola.repositories.FuncionarioRepository;
import me.edurevsky.controleescola.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Temporary class to prevent register the same cpf
 */
@Component
public class CpfHandler {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public CpfHandler(
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            FuncionarioRepository funcionarioRepository
    ) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public void ifAlreadyRegistered_ThrowException(String cpf) {
        if (alunoRepository.existsByCpf(cpf) || professorRepository.existsByCpf(cpf) || funcionarioRepository.existsByCpf(cpf)) {
            throw new CpfJaExisteException(String.format("O CPF %s já está em uso", cpf));
        }
    }

}
