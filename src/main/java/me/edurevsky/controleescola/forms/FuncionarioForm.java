package me.edurevsky.controleescola.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;

@Getter
@Setter
public class FuncionarioForm {
    
    @NotBlank
    private String nome;

    @CPF
    private String cpf;

    @NotNull
    private BigDecimal salario;

    @NotNull
    private Turno horarioDeTrabalho;

    public static Funcionario convertToFuncionario(FuncionarioForm funcionarioForm) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioForm.getNome());
        funcionario.setCpf(funcionarioForm.getCpf());
        funcionario.setSalario(funcionarioForm.getSalario());
        funcionario.setHorarioDeTrabalho(funcionarioForm.getHorarioDeTrabalho());
        return funcionario;
    }

}
