package me.edurevsky.controleescola.utils;

public class GeradorDeEmail {
    
    public static String gerarEmailParaAluno(String nome) {
        String base = nome.replace(" ", "").toLowerCase();
        String dominio = "@aluno.com";
        return base + dominio;
    }

    public static String gerarEmailParaProfessor(String nome) {
        String base = nome.replace(" ", "").toLowerCase();
        String dominio = "@professor.com";
        return base + dominio;
    }

}
