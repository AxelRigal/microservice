package com.microservice.articlesservice.model ;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
//@JsonFilter("monFiltreDynamique")
public class Article {
    @Id
    //@GeneratedValue
    private int id;
    private String nom;
    private int prix;
    //informations que nous ne souhaitons pas exposer
    private int prixAchat;
    public Article(int id, String nom, int prix, int prixAchat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
    }

    public Article() {

    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Override
    public String toString(){
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix'=" + prix + '\'' +
                "";
    }
}



