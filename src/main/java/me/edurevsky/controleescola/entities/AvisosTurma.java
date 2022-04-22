package me.edurevsky.controleescola.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "avisos_turma")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvisosTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Turma turma;

    private String nomeUsuario;

    private String mensagem;
    private LocalDateTime data;

    public String getDataFormatada() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
