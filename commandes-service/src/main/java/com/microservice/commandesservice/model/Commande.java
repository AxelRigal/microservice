package com.microservice.commandesservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Commande {
    @Id
    private int id;
    @Column
    private String nom;
    @Column
    private int prix;
    @Column(name="numeroarticle")
    private int numeroArticle;
    @Column
    private int quantite;
    @Temporal(TemporalType.DATE)
    @Column(name="datecommande")
    private Date dateCommande;




    public Commande(int id, String nom, int prix, int numeroArticle, int quantite, Date dateCommande){
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.numeroArticle = numeroArticle;
        this.quantite = quantite;
        this.dateCommande = dateCommande;
    }
    public Commande(){};


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNumeroArticle() {
        return numeroArticle;
    }

    public void setNumeroArticle(int numeroArticle) {
        this.numeroArticle = numeroArticle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Override
    public String toString(){
        return "Commande{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", numeroArticle '" + numeroArticle  + '\'' +
                ", quantite '" + quantite  + '\'' +
                ", prix'=" + prix + '\'' +
                ", date'="+ dateCommande + '\'' +
                "";
    }
}
