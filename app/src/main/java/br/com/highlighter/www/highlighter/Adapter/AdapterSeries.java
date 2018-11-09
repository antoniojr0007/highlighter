package br.com.highlighter.www.highlighter.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        holder.textNomeSerie.setText(listaSeries.get(position).getNome_serie().toString());
        holder.textProdutora.setText(listaSeries.get(position).getProdutora().toString());

        if(listaSeries.get(position).getImagem() != null){
            holder.imageSerie.setImageBitmap(listaSeries.get(position).getImagem());
        }else{
            Picasso.get().load(R.drawable.padrao).into(holder.imageSerie);
        }
        }

    @Override
    public int getItemCount() {
        return listaSeries.size();
        }

    public class SeriesHolder extends RecyclerView.ViewHolder {

    TextView textNomeSerie, textProdutora;
    ImageView imageSerie;

    public SeriesHolder(@NonNull View itemView) {
        super(itemView);

        textNomeSerie = itemView.findViewById(R.id.textNomeSerie);
        textProdutora = itemView.findViewById(R.id.textProdutora);
        imageSerie = itemView.findViewById(R.id.imageSerie);
    }
}
}
