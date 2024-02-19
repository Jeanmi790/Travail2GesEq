package classes.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.travail2geseq.R;

public class EquipesViewHolder extends RecyclerView.ViewHolder {

        private TextView nomEquipe;
        private ImageView logoEquipe;

        public EquipesViewHolder(View itemView) {
            super(itemView);
            nomEquipe = itemView.findViewById(R.id.nomEquipe);
            logoEquipe = itemView.findViewById(R.id.logoEquipe);
        }

}
