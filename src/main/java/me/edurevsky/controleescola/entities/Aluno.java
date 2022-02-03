package me.edurevsky.controleescola.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_alunos")
@EqualsAndHashCode(callSuper = false)
@Data
public class Aluno extends PessoaFisica {

    private String email;

    private LocalDate dataMatricula;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

}
