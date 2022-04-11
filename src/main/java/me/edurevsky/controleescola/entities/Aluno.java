package me.edurevsky.controleescola.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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

    public String getMatriculaFormatada() {
        return dataMatricula.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getCpf() {
        return super.getCpf();
    }
}
