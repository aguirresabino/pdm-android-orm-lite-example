package com.example.aguirresabino.ormliteexemplocrud.model;

import com.j256.ormlite.field.DatabaseField;

public class LivroModel {

    @DatabaseField(generatedId = true, columnName = "id")
    public int id;
    @DatabaseField(columnName = "titulo")
    public String titulo;
    @DatabaseField(columnName = "autor")
    public String autor;

    public LivroModel(){}

    public LivroModel(String titulo, String autor){
        this.titulo = titulo;
        this.autor = autor;
    }

}
