package br.com.highlighter.www.highlighter.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Filme {

    private int id;
    private String nome_filme;
    private String categoria;
    private String descricao;
    private String produtora;
    private String curtidas;
    private String descurtidas;
    private String dadosImagem;
    private Bitmap imagem;
    private String urlimagem;

    public Filme() {

    }

    public Filme(int id, String nome_filme, String categoria, String descricao, String produtora, String curtidas, String descurtidas, String dadosImagem, Bitmap imagem, String urlimagem) {
        this.id = id;
        this.nome_filme = nome_filme;
        this.categoria = categoria;
        this.descricao = descricao;
        this.produtora = produtora;
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

    public String getNome_filme() {
        return nome_filme;
    }

    public void setNome_filme(String nome_filme) {
        this.nome_filme = nome_filme;
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

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
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
