package com.example.tp_dbwithroom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "contact")
public class Contact {
 @PrimaryKey
 @NonNull
 @ColumnInfo(name = "contact")
 private String nom;
 private String prenom;
 private String numero;
 private String adresses;
    public Contact(@NonNull String nom, String prenom, String numero,String adresses) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.adresses=adresses;
    }

    public String getAdresses() {
        return adresses;
    }

    public void setAdresses(String adresses) {
        this.adresses = adresses;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}