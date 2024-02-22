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

public interface IServer {

    @GET("/H2024/420636RI/GR02/j_hebert/php/getEquipes.php")
    Call<List<Equipe>> getListeEquipes();

    @GET("H2024/420636RI/GR02/j_hebert/php/getJoueursEquipe.php")
    Call<List<Joueur>> getListeJoueursEquipe(@Query("idEquipe") int idEquipe);


    @POST("H2024/420636RI/GR02/j_hebert/php/addJoueur.php")
    @FormUrlEncoded
    Call<Boolean> ajoutJoueur(@Field("idJoueur") int idJoueur,
                              @Field("nom") String nom,
                              @Field("prenom") String prenom,
                              @Field("idEquipe") int idEquipe);

}
