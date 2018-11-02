package br.com.highlighter.www.highlighter.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import br.com.highlighter.www.highlighter.Model.Serie;
import br.com.highlighter.www.highlighter.R;

//import com.squareup.picasso.Picasso;

public class AdapterSeries extends RecyclerView.Adapter<AdapterSeries.SeriesHolder>{
        List<Serie> listaSeries;
        public AdapterSeries(List<Serie> listaSeries){
        this.listaSeries = listaSeries;
        }
    @NonNull
    @Override
    public SeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_serie, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
        );

        view.setLayoutParams(layoutParams);

        return new SeriesHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull SeriesHolder holder, int position) {
        holder.textNomeSerie.setText(listaSeries.get(position).getNome_Serie().toString());

        //Picasso.get().load(R.drawable.padrao).into(holder.imageSeire);
        }

    @Override
    public int getItemCount() {
        return listaSeries.size();
        }

    public class SeriesHolder extends RecyclerView.ViewHolder {

    TextView textNomeSerie;
    ImageView imageSerie;

    public SeriesHolder(@NonNull View itemView) {
        super(itemView);

        textNomeSerie = itemView.findViewById(R.id.textNomeSerie);
        imageSerie = itemView.findViewById(R.id.imageSerie);
    }
}
}
