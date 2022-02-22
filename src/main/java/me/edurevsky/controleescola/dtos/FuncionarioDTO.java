package me.edurevsky.controleescola.dtos;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
public class FuncionarioDTO {

    private String nome;
    private String cpf;
    private BigDecimal salario;
    private Turno horarioDeTrabalho;
    private String cargo;

    public FuncionarioDTO(@NotNull Funcionario funcionario) {
       this.nome = funcionario.getNome();
       this.cpf = funcionario.getCpf();
       this.salario = funcionario.getSalario();
       this.horarioDeTrabalho = funcionario.getHorarioDeTrabalho();
       this.cargo = funcionario.getCargo().getCargo();
    }

}
