package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

// @Entity
// @Table(name = "tb_professores")
@EqualsAndHashCode(callSuper = false)
@Data
public class Professor extends PessoaFisica {

    private BigDecimal salario;

    // @ManyToMany
    // @JoinTable(name = "prof_turmas")
    // private List<Turma> turmas;

}
