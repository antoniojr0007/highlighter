package br.com.highlighter.www.highlighter.Model;

public class Serie {
    private String curtidas;
    private String descricao;
    private String descurtidas;
    private String genero;
    private String imagem;
    private String nome_Serie;
    private String categoria;
    private String urlimagem;
    private String produtora;

    public Serie() {

    }

    public Serie(String curtidas, String descricao, String descurtidas, String genero, String imagem, String nome_Serie, String categoria, String urlimagem, String produtora) {
        this.curtidas = curtidas;
        this.descricao = descricao;
        this.descurtidas = descurtidas;
        this.genero = genero;
        this.imagem = imagem;
        this.nome_Serie = nome_Serie;
        this.categoria = categoria;
        this.urlimagem = urlimagem;
        this.produtora = produtora;
    }

    public String getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(String curtidas) {
        this.curtidas = curtidas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescurtidas() {
        return descurtidas;
    }

    public void setDescurtidas(String descurtidas) {
        this.descurtidas = descurtidas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome_Serie() {
        return nome_Serie;
    }

    public void setNome_Serie(String nome_Serie) {
        this.nome_Serie = nome_Serie;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrlimagem() {
        return urlimagem;
    }

    public void setUrlimagem(String urlimagem) {
        this.urlimagem = urlimagem;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }
}
