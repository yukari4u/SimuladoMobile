package com.example.simuladoapp;

public class Filme {
    private int id;
    private String titulo;
    private String diretor;
    private int ano;
    private int nota;
    private String genero;
    private boolean cinema;

    public Filme() {}

    public Filme(String titulo, String diretor, int ano, int nota, String genero, boolean cinema) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.ano = ano;
        this.nota = nota;
        this.genero = genero;
        this.cinema = cinema;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public String getDiretor() { return diretor; }
    public int getAno() { return ano; }
    public int getNota() { return nota; }
    public String getGenero() { return genero; }
    public boolean isCinema() { return cinema; }
}
