package br.com.highlighter.www.highlighter.Config;

public class Servidor {
    public static String mostrarServidor(){
        //String servidor = "https://highlighter.azurewebsites.net/";
        //String servidor = "https://www.highlighter.online/";
        String servidor = "https://moreirathunay.000webhostapp.com/";
        //String servidor = "http://192.168.0.2/Thunay/"
        return servidor;
    }



    public static String ConsultaPerfil(){

        //String ConsultaPerfil = "https://highlighter.azurewebsites.net/consultarUsuario.php?";
        //String ConsultaPerfil = "https://www.highlighter.online/consultarUsuario.php?";
        String ConsultaPerfil = "https://moreirathunay.000webhostapp.com/consultarUsuario.php?";

        return ConsultaPerfil;
    }

    public static String ConsultaLivro(){

        //String ConsultaLivro = "https://highlighter.azurewebsites.net/listarLivros.php";
        //String ConsultaLivro = "https://highlighter.online/listarLivros.php";
        String ConsultaLivro = "https://moreirathunay.000webhostapp.com/listarLivrosImagemPorCategoria.php?";

        return ConsultaLivro;
    }

    public static String MostraDadosLivro(){

        //String MostraDadosLivro = "https://highlighter.azurewebsites.net/listarLivros.php?nomeLivro=nomeLivro";
        //String MostraDadosLivro = "https://highlighter.online/listarLivros.php?nomeLivro=nomeLivro";
        String MostraDadosLivro = "https://moreirathunay.000webhostapp.com/listarLivros.php?nomeLivro=nomeLivro";

        return MostraDadosLivro;
    }

    public static String ConsultaSerie(){

        //String ConsultaSerie = "https://highlighter.azurewebsites.net/listarSeries.php";
        //String ConsultaSerie = "https://highlighter.online/listarSeries.php";
        String ConsultaSerie = "https://moreirathunay.000webhostapp.com/listarSeries.php";

        return ConsultaSerie;
    }

    public static String ConsultaFilme(){

        //String ConsultaFilme = "https://highlighter.azurewebsites.net/listarFilmes.php";
        //String ConsultaFilme = "https://highlighter.online/listarFilmes.php";
        String ConsultaFilme = "https://moreirathunay.000webhostapp.com/listarFilmes.php";

        return ConsultaFilme;
    }
}
