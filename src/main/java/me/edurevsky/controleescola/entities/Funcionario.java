package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_funcionarios")
@EqualsAndHashCode(callSuper = false)
@Data
public class Funcionario extends PessoaFisica {

    private BigDecimal salario;

    @JsonIgnore
    @ManyToOne
    private Cargo cargo;

}
