package me.edurevsky.controleescola.entities.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class HorarioDeTrabalho {

    @Column(name = "horario_inicio")
    private LocalTime horarioInicio;

    @Column(name = "horario_final")
    private LocalTime horarioFinal;

    public HorarioDeTrabalho() {

    }

    public HorarioDeTrabalho(LocalTime horarioInicio, LocalTime horarioFinal) {
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }
}
