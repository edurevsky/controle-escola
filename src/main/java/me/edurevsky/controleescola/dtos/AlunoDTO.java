package me.edurevsky.controleescola.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Aluno;
import me.edurevsky.controleescola.entities.enums.Turno;
import me.edurevsky.controleescola.utils.GeradorDeEmail;

/**
 * Classe responsável por receber apenas os
 * dados necessários de um aluno.
 */
public class AlunoDTO {

    @NotBlank
    private String nome;

    @CPF
    private String cpf;

    @NotNull
    private Turno turno;

    @NotNull
    private Long turma;

    public static Aluno convertToAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setTurno(alunoDTO.getTurno());
        aluno.setTurma(null);
        aluno.setDataMatricula(LocalDate.now());
        aluno.setEstaAtivo(true);
        aluno.setEmail(GeradorDeEmail.gerar(alunoDTO.getNome()));
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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public void setTurma(Long turma) {
        this.turma = turma;
    }

    public Long getTurma() {
        return turma;
    }

}
