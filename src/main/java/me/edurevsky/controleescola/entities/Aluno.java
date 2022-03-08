package me.edurevsky.controleescola.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import me.edurevsky.controleescola.entities.enums.Turno;

@Entity
@Table(name = "tb_alunos")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno extends PessoaFisica {

    private String email;

    private Boolean estaAtivo; 

    private LocalDate dataMatricula;

    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

}
