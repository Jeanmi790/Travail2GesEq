package classes.interfaces;

import java.util.List;

import classes.objects.Equipe;
import classes.objects.Joueur;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IServer {

    @GET("/getJoueur.php")
    Call<List<Equipe>> getListeEquipes();
    @GET("/getJoueursEquipe.php")
    Call<List<Joueur>> getListeJoueursEquipe();



    @POST("/addJoueur.php")
    @FormUrlEncoded
    Call<Boolean> ajoutJoueur(@Field("nom") String nom,
                             @Field("prenom") String prenom);

}
