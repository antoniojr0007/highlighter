package br.com.highlighter.www.highlighter.Config;

public class Servidor {
    public static String mostrarServidor(){
        String servidor = "https://highlighter.azurewebsites.net/";
        //String servidor = "http://192.168.0.16/highlighter/";
        //String servidor = "http://192.168.0.2/Thunay/"
        return servidor;
    }



    public static String ConsultaPerfil(){

        String ConsultaPerfil = "https://highlighter.azurewebsites.net/consultarUsuario.php?";

        return ConsultaPerfil;
    }

    public static String ConsultaLivro(){

        String ConsultaLivro = "https://highlighter.azurewebsites.net/listarLivros.php";

        return ConsultaLivro;
    }

    public static String MostraDadosLivro(){

        String MostraDadosLivro = "https://highlighter.azurewebsites.net/listarLivros.php?nomeLivro=nomeLivro";

        return MostraDadosLivro;
    }

    public static String ConsultaSerie(){

        String ConsultaSerie = "https://highlighter.azurewebsites.net/listarSeries.php";

        return ConsultaSerie;
    }

    public static String ConsultaFilme(){

        String ConsultaFilme = "https://highlighter.azurewebsites.net/listarFilmes.php";

        return ConsultaFilme;
    }
}
