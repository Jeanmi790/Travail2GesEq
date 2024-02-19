package classes.objects;

public class Equipe {


    private int idEquipe;
    private String nomEquipe;
    private String logo;

    public Equipe(String nomEquipe, String logo) {
        this.nomEquipe = nomEquipe;
        this.logo = logo;
    }

    public Equipe(int idEquipe, String nomEquipe, String logo) {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
