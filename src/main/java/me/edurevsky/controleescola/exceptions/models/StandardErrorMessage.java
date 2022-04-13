package me.edurevsky.controleescola.exceptions.models;

import java.util.Date;

import lombok.Data;

@Data
public class StandardErrorMessage {
    
    private Date timestamp;
    private String message;

    public StandardErrorMessage(Exception e) {
        this.timestamp = new Date();
        this.message = e.getMessage();
    }
}
