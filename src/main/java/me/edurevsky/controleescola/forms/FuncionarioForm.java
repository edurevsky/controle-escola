package me.edurevsky.controleescola.forms;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.entities.embeddables.HorarioDeTrabalho;
import me.edurevsky.controleescola.validation.AlreadyRegisteredCpf;
import org.hibernate.validator.constraints.br.CPF;

import me.edurevsky.controleescola.entities.Funcionario;
import org.springframework.beans.BeanUtils;

@Data
public class FuncionarioForm {
    
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @CPF(message = "O CPF inserido é inválido")
    @AlreadyRegisteredCpf
    private String cpf;

    @NotNull(message = "O salário precisa ser preenchido")
    private BigDecimal salario;

    private LocalTime horarioInicio;

    private LocalTime horarioFinal;

    private Cargo cargo;

    public FuncionarioForm(String nome, String cpf, BigDecimal salario, LocalTime horarioInicio, LocalTime horarioFinal, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.cargo = cargo;
    }

    public static Funcionario convertToFuncionario(FuncionarioForm funcionarioForm) {
        Funcionario funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioForm, funcionario);

        HorarioDeTrabalho horarioDeTrabalho = new HorarioDeTrabalho();
        horarioDeTrabalho.setHorarioInicio(funcionarioForm.getHorarioInicio());
        horarioDeTrabalho.setHorarioFinal(funcionarioForm.getHorarioFinal());

        funcionario.setHorarioDeTrabalho(horarioDeTrabalho);
        return funcionario;
    }

    public FuncionarioForm loadFromFuncionario(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.salario = funcionario.getSalario();
        this.horarioFinal = funcionario.getHorarioDeTrabalho().getHorarioFinal();
        this.horarioInicio = funcionario.getHorarioDeTrabalho().getHorarioInicio();
        this.cargo = funcionario.getCargo();
        return this;
    }
}
