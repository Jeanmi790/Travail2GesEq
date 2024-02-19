package classes.adapters;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import classes.objects.Equipe;
import classes.viewholder.EquipesViewHolder;

public class AdapterListeEquipe extends RecyclerView.Adapter<EquipesViewHolder>{
    private List<Equipe> listeEquipe;


    public AdapterListeEquipe(List<Equipe> listeEquipe) {
        this.listeEquipe = listeEquipe;

    }


    @NonNull
    @Override
    public EquipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EquipesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listeEquipe.size();
    }
}
