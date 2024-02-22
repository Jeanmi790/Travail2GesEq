package com.example.travail2geseq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
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
import java.util.logging.Logger;

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

                listeEquipe = response.body();
                aListeEquipe.setListeEquipe(listeEquipe);
                aListeEquipe.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Equipe>> call, Throwable t) {
                Logger.getLogger("ShowEquipesActivity").warning(t.getMessage());
                Toast.makeText(showEquipesContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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