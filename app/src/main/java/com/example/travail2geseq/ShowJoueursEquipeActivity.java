package com.example.travail2geseq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import classes.RetrofitInstance;
import classes.adapters.AdapterListeJoueur;
import classes.interfaces.IServer;
import classes.objects.Joueur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowJoueursEquipeActivity extends AppCompatActivity {

    Context showJoueursEquipeContext;
    RecyclerView rvJoueursEquipe;
    AdapterListeJoueur aListeJoueur;
    List<Joueur> listeJoueur = new ArrayList<>();
    List<Joueur> listeJoueurEquipe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joueurs_equipe);
        LoadRefsForAttributes();
    }

    private void LoadRefsForAttributes() {
        GetListeJoueursFromServer();
        rvJoueursEquipe = findViewById(R.id.rvListeJoueurs);
        showJoueursEquipeContext = this;
        rvJoueursEquipe.setHasFixedSize(true);
        rvJoueursEquipe.setLayoutManager(new LinearLayoutManager(showJoueursEquipeContext));
        aListeJoueur = new AdapterListeJoueur(listeJoueurEquipe);
        rvJoueursEquipe.setAdapter(aListeJoueur);

    }

    private void GetListeJoueursFromServer() {
        IServer iServer = RetrofitInstance.getRetrofitInstance().create(IServer.class);

        Intent intent = getIntent();
        int id = intent.getIntExtra("idEquipe", 0);

        Call<List<Joueur>> call = iServer.getListeJoueursEquipe(id);

        call.enqueue(new Callback<List<Joueur>>() {
            @Override
            public void onResponse(Call<List<Joueur>> call, Response<List<Joueur>> response) {
                listeJoueurEquipe = response.body();
                aListeJoueur.setListeJoueur(listeJoueurEquipe);
                aListeJoueur.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Joueur>> call, Throwable t) {
                Toast.makeText(showJoueursEquipeContext, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });
//        for (Joueur joueur : listeJoueur) {
//            if (joueur.getIdEquipe() == id) {
//                listeJoueurEquipe.add(joueur);
//            }
//        }

    }




}