package me.edurevsky.controleescola.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_alunos")
@EqualsAndHashCode(callSuper = false)
@Data
public class Aluno extends PessoaFisica {

    private String email;

    private Boolean estaAtivo; 

    private LocalDate dataMatricula;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

}
