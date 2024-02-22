package classes.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travail2geseq.R;

import java.util.List;

import classes.objects.Joueur;

public class JoueursViewHolder  extends RecyclerView.ViewHolder{



    TextView tvNomJoueur, tvPrenomJoueur;
    ImageView imgPhotoJoueur;
List<Joueur> listeJoueurs;

    public JoueursViewHolder(@NonNull View itemView, List<Joueur> listeJoueurs) {
        super(itemView);
        tvNomJoueur = itemView.findViewById(R.id.tvNomJoueur);
        tvPrenomJoueur = itemView.findViewById(R.id.tvPrenomJoueur);
        imgPhotoJoueur = itemView.findViewById(R.id.iPhotoJoueur);
        imgPhotoJoueur.setImageResource(R.drawable.ic_avatar);
        this.listeJoueurs = listeJoueurs;

    }
    public TextView getTvNomJoueur() {
        return tvNomJoueur;
    }

    public void setTvNomJoueur(TextView tvNomJoueur) {
        this.tvNomJoueur = tvNomJoueur;
    }
    public void setTvNomJoueur(String nom) {
        tvNomJoueur.setText(nom);
    }


    public TextView getTvPrenomJoueur() {
        return tvPrenomJoueur;
    }

    public void setTvPrenomJoueur(TextView tvPrenomJoueur) {
        this.tvPrenomJoueur = tvPrenomJoueur;
    }

    public void setTvPrenomJoueur(String prenom) {
        tvPrenomJoueur.setText(prenom);
    }


}
