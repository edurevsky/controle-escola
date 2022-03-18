package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.*;
import me.edurevsky.controleescola.entities.enums.Turno;

@Entity
@Table(name = "tb_funcionarios")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends PessoaFisica {

    private BigDecimal salario;

    private Turno horarioDeTrabalho;

    @ManyToOne
    private Cargo cargo;

}
