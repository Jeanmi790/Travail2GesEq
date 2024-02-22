package com.example.travail2geseq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import classes.RetrofitInstance;
import classes.adapters.AdapterListeEquipe;
import classes.interfaces.IEquipe;
import classes.interfaces.IServer;
import classes.objects.Equipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowEquipesActivity extends AppCompatActivity implements IEquipe {

    Context showEquipesContext;
    RecyclerView rvEquipes;
    AdapterListeEquipe aListeEquipe;

    ActivityResultLauncher<Intent> arlShowEquipe;
    List<Equipe> listeEquipe  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_equipes);
        LoadRefsForAttributes();
        MakeResultLaucher();
    }

    @Override
    public void OnClickEquipe(int positionInList, Equipe equipe) {

        Intent intent = new Intent(showEquipesContext, ShowJoueursEquipeActivity.class);
        intent.putExtra("idEquipe", listeEquipe.get(positionInList).getIdEquipe());
        intent.putExtra("nomEquipe", listeEquipe.get(positionInList).getNomEquipe());
        intent.putExtra("logo", listeEquipe.get(positionInList).getLogo());
        arlShowEquipe.launch(intent);
    }


    private void LoadRefsForAttributes() {
        //GetListeEquipesFromlocal();
        GetListeEquipesFromServer();
        rvEquipes = findViewById(R.id.rvEquipes);
        showEquipesContext = this;
        rvEquipes.setHasFixedSize(true);
        rvEquipes.setLayoutManager(new LinearLayoutManager(showEquipesContext));

        aListeEquipe = new AdapterListeEquipe( listeEquipe,ShowEquipesActivity.this);
        rvEquipes.setAdapter(aListeEquipe);

    }

    private void GetListeEquipesFromServer() {
        IServer iServer = RetrofitInstance.getRetrofitInstance().create(IServer.class);
        Call<List<Equipe>> call = iServer.getListeEquipes();
        call.enqueue(new Callback<List<Equipe>>() {

            @Override
            public void onResponse(Call<List<Equipe>> call, Response<List<Equipe>> response) {

                if(!response.isSuccessful()) {
                    return;
                }
                listeEquipe = response.body();
                aListeEquipe = new AdapterListeEquipe(listeEquipe, ShowEquipesActivity.this);
                rvEquipes.setAdapter(aListeEquipe);

            }

            @Override
            public void onFailure(Call<List<Equipe>> call, Throwable t) {
                Toast.makeText(showEquipesContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetListeEquipesFromlocal() {

    listeEquipe.add(new Equipe(1, "Les remparts de Québec", "https://assets.leaguestat.com/lhjmq/logos/168x127/9_193.png"));
    listeEquipe.add(new Equipe(2, "Les Tigres de Victoriaville", "https://assets.leaguestat.com/lhjmq/logos/168x127/17_193.png"));
    listeEquipe.add(new Equipe(3, "Les Voltigeurs de Drummondville", "https://assets.leaguestat.com/lhjmq/logos/168x127/14_193.png"));
    listeEquipe.add(new Equipe(4, "Les Cataractes de Shawinigan", "https://assets.leaguestat.com/lhjmq/logos/168x127/2_193.png"));
    listeEquipe.add(new Equipe(5, "Les Olympiques de Gatineau", "https://assets.leaguestat.com/lhjmq/logos/168x127/1_197.png"));
    listeEquipe.add(new Equipe(6, "Les Foreurs de Val-d'Or", "https://assets.leaguestat.com/lhjmq/logos/168x127/18_193.png"));

    }

    private void MakeResultLaucher(){
        arlShowEquipe = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Toast.makeText(showEquipesContext, "Retour au menu principal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
        }
        return true;
    }
    @Override
    public void onBackPressed() {

        setResult(RESULT_CANCELED);
        finish();
    }

}