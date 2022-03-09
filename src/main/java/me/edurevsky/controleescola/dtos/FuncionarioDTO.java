package me.edurevsky.controleescola.dtos;

import lombok.Getter;
import lombok.Setter;
import me.edurevsky.controleescola.entities.Cargo;
import me.edurevsky.controleescola.entities.Funcionario;
import me.edurevsky.controleescola.entities.enums.Turno;

@Getter
@Setter
public class FuncionarioDTO {

    private String nome;
    private String cpf;
    private Turno horarioDeTrabalho;
    private Cargo cargo;

    public FuncionarioDTO() {

    }

    public FuncionarioDTO(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.horarioDeTrabalho = funcionario.getHorarioDeTrabalho();
        this.cargo = funcionario.getCargo();
    }

}
