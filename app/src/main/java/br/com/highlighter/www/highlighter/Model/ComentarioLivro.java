package br.com.highlighter.www.highlighter.Model;

public class ComentarioLivro {

    private String comentario;
    private String data;
    private String nome_livro;
    private String urlUsuario;
    private String usuario;

    public ComentarioLivro() {
    }

    public ComentarioLivro(String comentario, String data, String nome_livro, String usuario) {
        this.comentario = comentario;
        this.data = data;
        this.nome_livro = nome_livro;
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome_livro() {
        return nome_livro;
    }

    public void setNome_livro(String nome_livro) {
        this.nome_livro = nome_livro;
    }

    public String getUrlUsuario() {
        return urlUsuario;
    }

    public void setUrlUsuario(String urlUsuario) {
        this.urlUsuario = urlUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
