package me.edurevsky.controleescola.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.validation.AlreadyRegisteredCpf;
import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class FuncionarioForm {
    
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @CPF(message = "O CPF inserido é inválido")
    @AlreadyRegisteredCpf
    private String cpf;

    @NotNull(message = "O salário precisa ser preenchido")
    private BigDecimal salario;

    private Turno horarioDeTrabalho;

    private Cargo cargo;

    public FuncionarioForm(String nome, String cpf, BigDecimal salario, Turno horarioDeTrabalho, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.horarioDeTrabalho = horarioDeTrabalho;
        this.cargo = cargo;
    }

    public static Funcionario convertToFuncionario(FuncionarioForm funcionarioForm) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioForm, funcionario);
        return funcionario;
    }

    public FuncionarioForm loadFromFuncionario(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.salario = funcionario.getSalario();
        this.horarioDeTrabalho = funcionario.getHorarioDeTrabalho();
        this.cargo = funcionario.getCargo();
        return this;
    }

}
