package br.com.highlighter.www.highlighter.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.highlighter.www.highlighter.Model.Livro;
import br.com.highlighter.www.highlighter.R;

//import com.squareup.picasso.Picasso;

public class AdapterLivros extends RecyclerView.Adapter<AdapterLivros.LivrosHolder>{

    List<Livro> listaLivros;
    public AdapterLivros(List<Livro> listaLivros){
        this.listaLivros = listaLivros;
    }
    @NonNull
    @Override
    public LivrosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_livro, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        vista.setLayoutParams(layoutParams);

        return new LivrosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull LivrosHolder holder, int position) {
        holder.textNome.setText(listaLivros.get(position).getNome_livro().toString());

        //Picasso.get().load(R.drawable.padrao).into(holder.imageLivro);
    }

    @Override
    public int getItemCount() {
        return listaLivros.size();
    }

    public class LivrosHolder extends RecyclerView.ViewHolder {

        TextView textNome;
        ImageView imageLivro;

        public LivrosHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textNome);
            imageLivro = itemView.findViewById(R.id.imageLivro);
        }
    }

}