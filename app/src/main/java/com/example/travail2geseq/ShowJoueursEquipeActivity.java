package com.example.travail2geseq;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import classes.RetrofitInstance;
import classes.adapters.AdapterListeJoueur;
import classes.interfaces.IServer;
import classes.objects.Equipe;
import classes.objects.Joueur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowJoueursEquipeActivity extends AppCompatActivity {

    TextView tvTitreNomEquipe;
    ImageView iLogo;
    Context showJoueursEquipeContext;
    RecyclerView rvJoueursEquipe;
    AdapterListeJoueur aListeJoueur;
    List<Joueur> listeJoueur = new ArrayList<>();
    List<Joueur> listeJoueurEquipe = new ArrayList<>();
    Equipe equipe;

    TextInputEditText tietNomJoueur, tietPrenomJoueur;

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
            AlertdialogBuilder("Ajouter un joueur", "Veuillez entrer les informations du joueur", new int[]{R.drawable.ic_avatar});
            Toast.makeText(this, "Ajouter un joueur", Toast.LENGTH_SHORT).show();
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
        rvJoueursEquipe.setHasFixedSize(true);GetListeJoueursFromServer();
        rvJoueursEquipe.setLayoutManager(new LinearLayoutManager(showJoueursEquipeContext));
        aListeJoueur = new AdapterListeJoueur(listeJoueurEquipe);
        rvJoueursEquipe.setAdapter(aListeJoueur);
        tietNomJoueur = findViewById(R.id.tietNomJoueur);
        tietPrenomJoueur = findViewById(R.id.tietPrenomJoueur);


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

    private void AddJoueur(String nom, String prenom) {
        Joueur joueur = new Joueur(0, nom, prenom, equipe.getIdEquipe());

        IServer iServer = RetrofitInstance.getRetrofitInstance().create(IServer.class);

        Call<Boolean> call = iServer.ajoutJoueur(0, nom, prenom, equipe.getIdEquipe());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                listeJoueurEquipe.add(joueur);
                aListeJoueur.notifyDataSetChanged();

            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                Toast.makeText(showJoueursEquipeContext, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AlertdialogBuilder(String title, String message, int[] icons) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.ad_template_add_joueur, null));
        builder.setTitle(title);
        builder.setIcon(icons[0]);
        builder.setMessage(message);
        builder.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (validateInfoJoueur()) {
                    AddJoueur(tietNomJoueur.getText().toString(), tietNomJoueur.getText().toString());
                    dialog.dismiss();
                }

            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(showJoueursEquipeContext, "Annuler", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private boolean ValidateNomEmpty() {
        if (Objects.requireNonNull(tietNomJoueur.getText()).toString().isEmpty()) {
            tietNomJoueur.setError("Le nom ne peut pas être vide");
            return false;
        }
        return true;
    }

    private boolean ValidatePrenomempty() {
        if (Objects.requireNonNull(tietPrenomJoueur.getText()).toString().isEmpty()) {
            tietPrenomJoueur.setError("Le prénom ne peut pas être vide");
            return false;
        }
        return true;
    }

    private boolean ValidateNomMinLength() {
        if (Objects.requireNonNull(tietNomJoueur.getText()).toString().length() < 2) {
            tietNomJoueur.setError("Le nom doit contenir au moins 2 caractères");
            return false;
        }
        return true;
    }

    private boolean ValidatePrenomMinLength() {
        if (Objects.requireNonNull(tietPrenomJoueur.getText()).toString().length() < 2) {
            tietPrenomJoueur.setError("Le prénom doit contenir au moins 2 caractères");
            return false;
        }
        return true;
    }

    private boolean ValidateNomMaxLength() {
        if (Objects.requireNonNull(tietNomJoueur.getText()).toString().length() > 50) {
            tietNomJoueur.setError("Le nom doit contenir au maximum 50 caractères");
            return false;
        }
        return true;
    }

    private boolean ValidatePrenomMaxLength() {
        if (Objects.requireNonNull(tietPrenomJoueur.getText()).toString().length() > 50) {
            tietPrenomJoueur.setError("Le prénom doit contenir au maximum 50 caractères");
            return false;
        }
        return true;
    }

    private boolean validateInfoJoueur() {

        return ValidateNomEmpty()
                && ValidatePrenomempty()
                && ValidateNomMinLength()
                && ValidatePrenomMinLength()
                && ValidateNomMaxLength()
                && ValidatePrenomMaxLength();

    }


}