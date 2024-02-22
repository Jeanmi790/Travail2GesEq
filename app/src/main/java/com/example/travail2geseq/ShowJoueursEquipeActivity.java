package com.example.travail2geseq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import classes.adapters.AdapterListeJoueur;
import classes.objects.Joueur;

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
        GetListeFromServer();
        rvJoueursEquipe = findViewById(R.id.rvListeJoueurs);
        showJoueursEquipeContext = this;
        rvJoueursEquipe.setHasFixedSize(true);
        rvJoueursEquipe.setLayoutManager(new LinearLayoutManager(showJoueursEquipeContext));
        aListeJoueur = new AdapterListeJoueur(listeJoueurEquipe);
        rvJoueursEquipe.setAdapter(aListeJoueur);

    }

    private void GetListeJoueursFromServer() {

        listeJoueur.add(new Joueur(1, "Tutchell", "Nélie", 1));
        listeJoueur.add(new Joueur(22, "Le Franc", "Amélie", 2));
        listeJoueur.add(new Joueur(3, "Almak", "Liè", 3));
        listeJoueur.add(new Joueur(4, "Lemar", "Léa", 4));
        listeJoueur.add(new Joueur(5, "O'Flaherty", "Maïlys", 5));
        listeJoueur.add(new Joueur(7, "Cambridge", "Illustrée", 1));
        listeJoueur.add(new Joueur(8, "Lemar", "Léa", 2));
        listeJoueur.add(new Joueur(9, "O'Flaherty", "Maïlys", 3));
        listeJoueur.add(new Joueur(10, "Kesey", "Illustrée", 4));
        listeJoueur.add(new Joueur(11, "Wardrop", "Léa", 5));
        listeJoueur.add(new Joueur(12, "O'Cater", "Maïlys", 1));
        listeJoueur.add(new Joueur(13, "Cambridge", "Illustrée", 2));
        listeJoueur.add(new Joueur(14, "Ferrelli", "Léa", 3));
        listeJoueur.add(new Joueur(15, "O'Olyet", "Maïlys", 4));
        listeJoueur.add(new Joueur(16, "Dannatt", "Noémie", 5));

    }

    private void GetListeFromServer() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("idEquipe", 0);
        for (Joueur joueur : listeJoueur) {
            if (joueur.getIdEquipe() == id) {
                listeJoueurEquipe.add(joueur);
            }
        }

    }


}