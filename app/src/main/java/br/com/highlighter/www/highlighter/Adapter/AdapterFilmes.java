package br.com.highlighter.www.highlighter.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        holder.textProdutora.setText(listaFilmes.get(position).getProdutora().toString());

        if(listaFilmes.get(position).getImagem() != null){
            holder.imageFilme.setImageBitmap(listaFilmes.get(position).getImagem());
        }else{
            Picasso.get().load(R.drawable.padrao).into(holder.imageFilme);
        }
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public class FilmesHolder extends RecyclerView.ViewHolder {

        TextView textNomeFilme, textProdutora;
        ImageView imageFilme;

        public FilmesHolder(@NonNull View itemView) {
            super(itemView);

            textNomeFilme = itemView.findViewById(R.id.textNome);
            textProdutora = itemView.findViewById(R.id.editTextProdutora);
            imageFilme = itemView.findViewById(R.id.imageFilme);
        }
    }
}
