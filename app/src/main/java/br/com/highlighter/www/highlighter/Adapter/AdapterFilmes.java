package br.com.highlighter.www.highlighter.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.highlighter.www.highlighter.Model.Filme;
import br.com.highlighter.www.highlighter.R;

//import com.squareup.picasso.Picasso;

public class AdapterFilmes extends RecyclerView.Adapter<AdapterFilmes.FilmesHolder> {

    List<Filme> listaFilmes;

    public AdapterFilmes(List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    @NonNull
    @Override
    public FilmesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filme, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        view.setLayoutParams(layoutParams);

        return new FilmesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmesHolder holder, int position) {
        holder.textNomeFilme.setText(listaFilmes.get(position).getNome_filme().toString());

        //Picasso.get().load(R.drawable.padrao).into(holder.imageFilme);
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public class FilmesHolder extends RecyclerView.ViewHolder {

        TextView textNomeFilme, textCategoria;
        ImageView imageFilme;

        public FilmesHolder(@NonNull View itemView) {
            super(itemView);

            textNomeFilme = itemView.findViewById(R.id.textNome);
            textCategoria = itemView.findViewById(R.id.textCategoria);
            imageFilme = itemView.findViewById(R.id.imageFilme);
        }
    }
}
