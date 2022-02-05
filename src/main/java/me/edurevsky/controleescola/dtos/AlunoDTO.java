package me.edurevsky.controleescola.dtos;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.Turma;
import me.edurevsky.controleescola.services.TurmaService;

/**
 * Classe responsável por receber apenas os
 * dados necessários de um aluno.
 */
public class AlunoDTO {

    private String nome;
    private String cpf;
    private Long idTurma;

    @Autowired
    private static TurmaService turmaService;

    public AlunoDTO() {

    }

    public static Aluno convertToAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setTurma(turmaService.buscarPorId(alunoDTO.getIdTurma()));
        aluno.setDataMatricula(LocalDate.now());
        aluno.setEmail("" /**TODO: Gerador de email */);
        return aluno;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public Long getIdTurma() {
        return idTurma;
    }

}
