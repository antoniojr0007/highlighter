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

import br.com.highlighter.www.highlighter.Model.ComentarioLivro;
import br.com.highlighter.www.highlighter.R;

public class AdapterComentarioLivros extends RecyclerView.Adapter<AdapterComentarioLivros.MyViewHolder>{
    private List<ComentarioLivro> comentarios;
    private Context context;

    public AdapterComentarioLivros(List<ComentarioLivro> comentarios, Context context) {
        this.comentarios = comentarios;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_comentario_livro, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ComentarioLivro comentarioLivro = comentarios.get(position);

        holder.usuario.setText(comentarioLivro.getUsuario());
        holder.comentario.setText(comentarioLivro.getComentario());
        //holder.nome_livro.setText(livro.getNome_livro());

        String urlFoto = comentarioLivro.getUrlUsuario();

        //Picasso.get().load(urlFoto).into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView usuario;
        TextView comentario;
        ImageView imagem;

        public MyViewHolder(View itemView){
            super(itemView);

            usuario = itemView.findViewById(R.id.textUsuario);
            comentario = itemView.findViewById(R.id.textComentario);
            imagem = itemView.findViewById(R.id.imageUsuario);
        }
    }
}