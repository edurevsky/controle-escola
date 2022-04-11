package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.edurevsky.controleescola.entities.embeddables.HorarioDeTrabalho;

@Entity
@Table(name = "tb_funcionarios")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends PessoaFisica {

    private BigDecimal salario;

    @ManyToOne
    private Cargo cargo;

    private HorarioDeTrabalho horarioDeTrabalho;

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
