package classes.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travail2geseq.R;

import java.util.List;

import classes.objects.Joueur;
import classes.viewholder.JoueursViewHolder;

public class AdapterListeJoueur extends RecyclerView.Adapter<JoueursViewHolder> {

    List<Joueur> listeJoueur;

    public AdapterListeJoueur(List<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    public void setListeJoueur(List<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    @NonNull
    @Override
    public JoueursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new JoueursViewHolder(inflater.inflate(R.layout.rv_template_joueur, parent, false), listeJoueur);

    }

    @Override
    public void onBindViewHolder(@NonNull JoueursViewHolder holder, int position) {
        holder.setTvNomJoueur(listeJoueur.get(position).getNom());
        holder.setTvPrenomJoueur(listeJoueur.get(position).getPrenom());

    }

    @Override
    public int getItemCount() {
        return listeJoueur.size();
    }
}
