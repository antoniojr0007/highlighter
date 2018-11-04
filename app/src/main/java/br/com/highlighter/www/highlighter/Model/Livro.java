package br.com.highlighter.www.highlighter.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Livro {

    private int id;
    private String nome_livro;
    private String categoria;
    private String descricao;
    private String autor;
    private String curtidas;
    private String descurtidas;
    private String dadosImagem;
    private Bitmap imagem;
    private String urlimagem;


    public Livro() {

    }

    public Livro(int id, String nome_livro, String categoria, String descricao, String autor, String curtidas, String descurtidas, String dadosImagem, Bitmap imagem, String urlimagem) {
        this.id = id;
        this.nome_livro = nome_livro;
        this.categoria = categoria;
        this.descricao = descricao;
        this.autor = autor;
        this.curtidas = curtidas;
        this.descurtidas = descurtidas;
        this.dadosImagem = dadosImagem;
        this.imagem = imagem;
        this.urlimagem = urlimagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_livro() {
        return nome_livro;
    }

    public void setNome_livro(String nome_livro) {
        this.nome_livro = nome_livro;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(String curtidas) {
        this.curtidas = curtidas;
    }

    public String getDescurtidas() {
        return descurtidas;
    }

    public void setDescurtidas(String descurtidas) {
        this.descurtidas = descurtidas;
    }

    public String getDadosImagem() {
        return dadosImagem;
    }

    public void setDadosImagem(String dadosImagem) {
        this.dadosImagem = dadosImagem;

        try{
            byte[] byteCode = Base64.decode(dadosImagem, Base64.DEFAULT);
            this.imagem = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public String getUrlimagem() {
        return urlimagem;
    }

    public void setUrlimagem(String urlimagem) {
        this.urlimagem = urlimagem;
    }
}
