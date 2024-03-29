package classes.objects;

import com.google.gson.annotations.SerializedName;

public class Joueur {

    @SerializedName("idJoueur")
    private int idJoueur;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("idEquipe")
    private int idEquipe;

    public Joueur(String nom, String prenom, int idEquipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.idEquipe = idEquipe;
    }

    public Joueur(int idJoueur, String nom, String prenom, int idEquipe) {
        this.idJoueur = idJoueur;
        this.nom = nom;
        this.prenom = prenom;
        this.idEquipe = idEquipe;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
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

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

}
