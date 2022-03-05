package me.edurevsky.controleescola.entities;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_professores")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends PessoaFisica {

    private BigDecimal salario;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas;

}
