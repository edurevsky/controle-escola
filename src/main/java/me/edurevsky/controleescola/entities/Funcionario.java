package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_funcionarios")
@EqualsAndHashCode(callSuper = false)
@Data
public class Funcionario extends PessoaFisica {

    private BigDecimal salario;

    @ManyToOne
    private Cargo cargo;

}
