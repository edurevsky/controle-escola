package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

// @Entity
// @Table(name = "tb_professores")
@Data
public class Professor {
    
    private Long id;

    private String nome;

    private String cpf;

    private BigDecimal salario;

    // @ManyToMany
    // @JoinTable(name = "prof_turmas")
    // private List<Turma> turmas;

}
