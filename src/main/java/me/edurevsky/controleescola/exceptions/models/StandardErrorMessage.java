package me.edurevsky.controleescola.exceptions.models;

import java.util.Date;

import lombok.Data;

@Data
public class StandardErrorMessage {
    
    private Date currrentDate;
    private String message;
    
}
