package me.edurevsky.controleescola.utils;

public class GeradorDeEmail {
    
    public static String gerar(String nome) {
        String base = nome.replace(" ", "").toLowerCase();
        String dominio = "@aluno.com";
        return base + dominio;
    }

}
