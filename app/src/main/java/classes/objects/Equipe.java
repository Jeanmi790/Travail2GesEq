package classes.objects;

import android.graphics.drawable.Drawable;

public class Equipe {


    private int idEquipe;
    private String nomEquipe;
    private Drawable logo;

    public Equipe(String nomEquipe, Drawable logo) {
        this.nomEquipe = nomEquipe;
        this.logo = logo;
    }

    public Equipe(int idEquipe, String nomEquipe, Drawable logo) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.logo = logo;
    }


    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }
}
