package me.edurevsky.controleescola.controllers.utils;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public record IdObject(Long id) {

    public Long getId() {
        return id;
    }

}
