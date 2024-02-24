package classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.travail2geseq.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.logging.Logger;

import classes.interfaces.IInteractionServer;
import classes.interfaces.IServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddJoueurDialog extends AlertDialog {

    TextInputEditText tietNomJoueur, tietPrenomJoueur;
    Button btnAddJoueur, btnCancelJoueur;
    int idEquipe;

    int isAddJoueurActivated = 0;

    EditTextValidator etvNomJoueur;

    EditTextValidator etvPrenomJoueur;

    IInteractionServer iInteractionServer;


    public AddJoueurDialog(@NonNull Context context) {
        super(context);
    }

    public AddJoueurDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public AddJoueurDialog(@NonNull Context context, int themeResId, int idEquipe) {
        super(context, themeResId);
        this.idEquipe = idEquipe;
        iInteractionServer = (IInteractionServer) context;

    }

    protected AddJoueurDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Ajouter un joueur");
        setContentView(getLayoutInflater().inflate(R.layout.ad_template_add_joueur, null));
        LoadRefsForAttributes();
    }


    private void LoadRefsForAttributes() {
        tietNomJoueur = findViewById(R.id.tietNomJoueur);
        tietPrenomJoueur = findViewById(R.id.tietPrenomJoueur);
        btnAddJoueur = findViewById(R.id.btnAddJoueur);
        btnCancelJoueur = findViewById(R.id.btnCancelJoueur);
        btnAddJoueur.setOnClickListener(this::AddJoueurToDB);
        btnCancelJoueur.setOnClickListener(this::CancelJoueur);

        etvNomJoueur = new EditTextValidator(tietNomJoueur, 3, 20, false, true,
                new String[]{"Le nom doit contenir au moins 3 caractères", "Le nom doit contenir au plus 20 caractères", "Le nom ne peut pas être vide", "Le champ ne doit contenir que des lettres"});
        etvPrenomJoueur = new EditTextValidator(tietPrenomJoueur, 3, 20, false, true,
                new String[]{"Le prénom doit contenir au moins 3 caractères", "Le prénom doit contenir au plus 20 caractères", "Le prénom ne peut pas être vide", "Le champ ne doit contenir que des lettres"});
    }


    private void AddJoueurToDB(View view) {
        if (etvNomJoueur.returnNumberOfErrors() == 0 && etvPrenomJoueur.returnNumberOfErrors() == 0) {
            IServer iServer = RetrofitInstance.getRetrofitInstance().create(IServer.class);

            Call<Boolean> call = iServer.ajoutJoueur( idEquipe ,Objects.requireNonNull(tietNomJoueur.getText()).toString(), Objects.requireNonNull(tietPrenomJoueur.getText()).toString());
            Log.d("AddJoueurDialog", "Info joueur: "+Objects.requireNonNull(tietNomJoueur.getText()).toString() + " " + Objects.requireNonNull(tietPrenomJoueur.getText()).toString() + " " + idEquipe);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                    Log.d("AddJoueurDialog", "onResponse: " + response.body());
                    iInteractionServer.OnAddJoueur();
                    Toast.makeText(getContext(), "Joueur ajouté", Toast.LENGTH_SHORT).show();
                    etvNomJoueur.ResetError();
                    etvPrenomJoueur.ResetError();
                    etvNomJoueur.ResetInput();
                    etvPrenomJoueur.ResetInput();
                    dismiss();


                }

                @Override
                public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(), "Erreur lors de l'ajout du joueur", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private boolean isAddJoueurActivated() {

        return isAddJoueurActivated == 0;
    }

    private void CancelJoueur(View view) {
        dismiss();
    }


}
