package com.ejemplo.model;

import java.util.ArrayList;
import java.util.List;

public class Fanfic {
    private int id;
    private String ao3Url;
    private String ao3WorkId;
    private String titulo;
    private String autor;
    private String ao3Rating;
    private int wordCount;
    private String finishedDate;
    private int userStars;
    private List<String> warnings = new ArrayList<>();
    private List<String> relationships = new ArrayList<>();
    private List<String> fandoms = new ArrayList<>();
    private List<String> categories = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAo3Url() {
        return ao3Url;
    }

    public void setAo3Url(String ao3Url) {
        this.ao3Url = ao3Url;
    }

    public String getAo3WorkId() {
        return ao3WorkId;
    }

    public void setAo3WorkId(String ao3WorkId) {
        this.ao3WorkId = ao3WorkId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAo3Rating() {
        return ao3Rating;
    }

    public void setAo3Rating(String ao3Rating) {
        this.ao3Rating = ao3Rating;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public int getUserStars() {
        return userStars;
    }

    public void setUserStars(int userStars) {
        this.userStars = userStars;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public List<String> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<String> relationships) {
        this.relationships = relationships;
    }

    public List<String> getFandoms() {
        return fandoms;
    }

    public void setFandoms(List<String> fandoms) {
        this.fandoms = fandoms;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
