package me.edurevsky.controleescola.forms;

import lombok.Data;
import me.edurevsky.controleescola.entities.Professor;
import me.edurevsky.controleescola.entities.Turma;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AvisosTurmaForm {

    @NotNull
    private Turma turma;

    @NotBlank
    private String nomeUsuario;

    @NotBlank
    private String mensagem;

    private LocalDateTime data;

    public AvisosTurmaForm(Turma turma, String nomeUsuario, String mensagem) {
        this.turma = turma;
        this.nomeUsuario = nomeUsuario;
        this.mensagem = mensagem;
        this.data = LocalDateTime.now();
    }
}
