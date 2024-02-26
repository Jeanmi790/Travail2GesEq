package com.example.travail2geseq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import classes.dialogs.AddJoueurDialog;
import classes.RetrofitInstance;
import classes.adapters.AdapterListeJoueur;
import classes.interfaces.IInteractionServer;
import classes.interfaces.IServer;
import classes.interfaces.IServerLocal;
import classes.objects.Equipe;
import classes.objects.Joueur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowJoueursEquipeActivity extends AppCompatActivity implements IInteractionServer {

    TextView tvTitreNomEquipe;
    ImageView iLogo;
    Context showJoueursEquipeContext;
    RecyclerView rvJoueursEquipe;
    AdapterListeJoueur aListeJoueur;
    List<Joueur> listeJoueur = new ArrayList<>();
    List<Joueur> listeJoueurEquipe = new ArrayList<>();
    Equipe equipe;


    AddJoueurDialog dAddJoueur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joueurs_equipe);
        LoadRefsForAttributes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_joueurs_equipe, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.imAddJoueur) {
            dAddJoueur.show();
            //Toast.makeText(this, "Ajouter un joueur", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadRefsForAttributes() {
        ReceiveIntent();
        tvTitreNomEquipe = findViewById(R.id.tvTitreNomEquipe);
        iLogo = findViewById(R.id.iLogo);
        SetLogoAndTitle();

        rvJoueursEquipe = findViewById(R.id.rvListeJoueurs);
        showJoueursEquipeContext = this;
        rvJoueursEquipe.setHasFixedSize(true);
        GetListeJoueursFromServer();
        rvJoueursEquipe.setLayoutManager(new LinearLayoutManager(showJoueursEquipeContext));
        aListeJoueur = new AdapterListeJoueur(listeJoueurEquipe);
        rvJoueursEquipe.setAdapter(aListeJoueur);

        dAddJoueur = new AddJoueurDialog(showJoueursEquipeContext, getTheme().getChangingConfigurations(), equipe.getIdEquipe());
        dAddJoueur.create();
        dAddJoueur.hide();

    }

    private void ReceiveIntent() {
        Intent intent = getIntent();
        equipe = new Equipe(
                intent.getIntExtra("idEquipe", 0),
                intent.getStringExtra("nomEquipe"),
                intent.getStringExtra("logo"));

    }

    private void SetLogoAndTitle() {
        Picasso.get().load(equipe.getLogo()).into(iLogo);
        tvTitreNomEquipe.setText(equipe.getNomEquipe());
    }

    private void GetListeJoueursFromServer() {
        //IServerLocal iServer = RetrofitInstance.getRetrofitInstance().create(IServerLocal.class);
        IServer iServer = RetrofitInstance.getRetrofitInstance().create(IServer.class);

        Call<List<Joueur>> call = iServer.getListeJoueursEquipe(equipe.getIdEquipe());

        call.enqueue(new Callback<List<Joueur>>() {
            @Override
            public void onResponse(@NonNull Call<List<Joueur>> call, @NonNull Response<List<Joueur>> response) {
                listeJoueurEquipe = response.body();
                aListeJoueur.setListeJoueur(listeJoueurEquipe);
                aListeJoueur.notifyDataSetChanged();


            }

            @Override
            public void onFailure(@NonNull Call<List<Joueur>> call, @NonNull Throwable t) {
                Toast.makeText(showJoueursEquipeContext, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void OnAddJoueur() {
        GetListeJoueursFromServer();

    }

    @Override
    protected void onResume() {
        super.onResume();
        GetListeJoueursFromServer();

    }

}