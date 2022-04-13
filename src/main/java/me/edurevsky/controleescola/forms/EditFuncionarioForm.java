package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.embeddables.HorarioDeTrabalho;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class EditFuncionarioForm {

	@NotBlank(message = "O nome não pode estar em branco")
	private String nome;

	private String cpf;

	@NotNull(message = "O salário precisa ser preenchido")
	private BigDecimal salario;

	private LocalTime horarioInicio;

	private LocalTime horarioFinal;

	private Cargo cargo;

	public EditFuncionarioForm loadFromFuncionario(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.cpf = funcionario.getCpf();
		this.salario = funcionario.getSalario();
		this.horarioInicio = funcionario.getHorarioDeTrabalho().getHorarioInicio();
		this.horarioFinal = funcionario.getHorarioDeTrabalho().getHorarioFinal();
		this.cargo = funcionario.getCargo();
		return this;
	}

	public static Funcionario update(Funcionario funcionario, EditFuncionarioForm editFuncionarioForm) {
		funcionario.setNome(editFuncionarioForm.getNome());
		funcionario.setSalario(editFuncionarioForm.getSalario());
		funcionario.setCargo(editFuncionarioForm.getCargo());

		HorarioDeTrabalho horarioDeTrabalho = new HorarioDeTrabalho();
		horarioDeTrabalho.setHorarioInicio(editFuncionarioForm.getHorarioInicio());
		horarioDeTrabalho.setHorarioFinal(editFuncionarioForm.getHorarioFinal());

		funcionario.setHorarioDeTrabalho(horarioDeTrabalho);
		return funcionario;
	}
}
