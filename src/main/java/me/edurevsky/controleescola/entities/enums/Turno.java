package me.edurevsky.controleescola.entities.enums;

public enum Turno {
    
    MATUTINO("Matutino"),
    VESPERTINO("Vespertino"),
    NOTURNO("Noturno");

    private String turno;

    private Turno(String turno) {
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }
    
}
