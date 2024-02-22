package classes.adapters;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travail2geseq.R;

import java.util.List;

import classes.interfaces.IEquipe;
import classes.objects.Equipe;
import classes.viewholder.EquipesViewHolder;

public class AdapterListeEquipe extends RecyclerView.Adapter<EquipesViewHolder>{


    private List<Equipe> listeEquipe;
    private IEquipe iEquipe;

    public AdapterListeEquipe(List<Equipe> listeEquipe, IEquipe iEquipe) {
        this.listeEquipe = listeEquipe;
        this.iEquipe = iEquipe;

    }
    public List<Equipe> getListeEquipe() {
        return listeEquipe;
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
