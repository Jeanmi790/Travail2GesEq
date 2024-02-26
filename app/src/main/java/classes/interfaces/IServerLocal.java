package classes.interfaces;

import java.util.List;

import classes.objects.Equipe;
import classes.objects.Joueur;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IServerLocal {

    @GET("/getEquipes.php")
    Call<List<Equipe>> getListeEquipes();

    @GET("/getJoueursEquipe.php")
    Call<List<Joueur>> getListeJoueursEquipe(@Query("idEquipe") int idEquipe);


    @POST("/addJoueur.php")
    @FormUrlEncoded
    Call<Boolean> ajoutJoueur(@Field("idEquipe") int idEquipe,
                              @Field("nom") String nom,
                              @Field("prenom") String prenom
    );

}
