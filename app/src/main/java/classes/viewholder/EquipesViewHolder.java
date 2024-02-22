package classes.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.travail2geseq.R;

import java.util.List;

import classes.interfaces.IEquipe;
import classes.objects.Equipe;

public class EquipesViewHolder extends RecyclerView.ViewHolder {


    private TextView tvNomEquipe;
    private ImageView imgLogoEquipe;
    private IEquipe iEquipe;
    private List<Equipe> listeEquipe;

    public EquipesViewHolder(View itemView, List<Equipe> listeEquipe) {
        super(itemView);
        iEquipe = (IEquipe) itemView.getContext();
        this.listeEquipe = listeEquipe;
        tvNomEquipe = itemView.findViewById(R.id.tvNomEquipe);
        imgLogoEquipe = itemView.findViewById(R.id.imgLogoEquipe);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEquipe.OnClickEquipe(getAdapterPosition(), listeEquipe.get(getAdapterPosition()));
            }
        });
    }

    public TextView getTvNomEquipe() {
        return tvNomEquipe;
    }

    public void setTvNomEquipe(TextView tvNomEquipe) {
        this.tvNomEquipe = tvNomEquipe;
    }

    public void setTvNomEquipe(String nomEquipe) {
        this.tvNomEquipe.setText(nomEquipe);
    }

    public ImageView getImgLogoEquipe() {
        return imgLogoEquipe;
    }

    public void setImgLogoEquipe(ImageView imgLogoEquipe) {
        this.imgLogoEquipe = imgLogoEquipe;
    }

    public void setImgLogoEquipe(Drawable logo) {
        this.imgLogoEquipe.setImageDrawable(logo);
    }

}
