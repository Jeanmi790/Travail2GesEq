package classes.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travail2geseq.R;

import java.util.List;

import classes.interfaces.IEquipe;
import classes.objects.Equipe;
import classes.viewholder.EquipesViewHolder;
import com.squareup.picasso.Picasso;

public class AdapterListeEquipe extends RecyclerView.Adapter<EquipesViewHolder> {


    private List<Equipe> listeEquipe;
    private final IEquipe iEquipe;

    public AdapterListeEquipe(List<Equipe> listeEquipe, IEquipe iEquipe) {
        this.listeEquipe = listeEquipe;
        this.iEquipe = iEquipe;

    }

    public List<Equipe> getListeEquipe() {
        return listeEquipe;
    }

    public void setListeEquipe(List<Equipe> listeEquipe) {
        this.listeEquipe = listeEquipe;
    }


    @NonNull
    @Override
    public EquipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new EquipesViewHolder(inflater.inflate(R.layout.rv_template_equipe, parent, false), listeEquipe);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipesViewHolder holder, int position) {
        holder.setTvNomEquipe(listeEquipe.get(position).getNomEquipe());
        holder.setImgLogoEquipe(listeEquipe.get(position).getLogo());
    }

    @Override
    public int getItemCount() {
        return listeEquipe.size();
    }


}
