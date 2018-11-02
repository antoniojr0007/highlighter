package br.com.highlighter.www.highlighter.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.highlighter.www.highlighter.Config.Servidor;
import br.com.highlighter.www.highlighter.Model.Usuario;
import br.com.highlighter.www.highlighter.R;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Integer id = 0;
    String nomeCompleto = "";
    String apelido = "";
    String genero = "";
    String dataNascimento = "";
    String email = "";
    String senha = "";
    String senhaCriptografada = "";

    EditText campoLogin, campoSenha;
    TextView campoCadastrar;
    Button buttonLogar;
    ProgressDialog progresso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("autenticar", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        campoLogin     = findViewById(R.id.editTextLogin);
        campoSenha     = findViewById(R.id.editTextSenha);
        campoCadastrar = findViewById(R.id.viewCadastrar);
        buttonLogar    = findViewById(R.id.buttonLogar);

        request = Volley.newRequestQueue(this);
    }

    public void carregarWebService(View view) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        progresso = new ProgressDialog(this);
        progresso.setMessage("Autenticando...");
        progresso.show();

        converterSenha();

        String url = Servidor.mostrarServidor() +"fazerLogin.php?apelido="+campoLogin.getText().toString()+"&senha="+senhaCriptografada;

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        request.add(jsonObjectRequest);
    }

    private void converterSenha() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
        byte messageDigestSenhaAdmin[] = algorithm.digest(campoSenha.getText().toString().getBytes("UTF-8"));
        StringBuilder hexStringSenhaAdmin = new StringBuilder();
        for (byte b : messageDigestSenhaAdmin) {
            hexStringSenhaAdmin.append(String.format("%02x", 0xFF & b));
        }
        senhaCriptografada = hexStringSenhaAdmin.toString();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();
        Toast.makeText(this, "Não foi possível conectar ao servidor!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();
        Usuario usuario = new Usuario();

        JSONArray jsonArray = response.optJSONArray("usuario");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setId(jsonObject.optInt("id"));
            usuario.setNomeCompleto(jsonObject.optString("nomeCompleto"));
            usuario.setApelido(jsonObject.optString("apelido"));
            usuario.setGenero(jsonObject.optString("genero"));
            usuario.setDataNascimento(jsonObject.optString("dataNascimento"));
            usuario.setEmail(jsonObject.optString("email"));
            usuario.setSenha(jsonObject.optString("senha"));
        }catch(JSONException e){
            e.printStackTrace();
        }

        id = usuario.getId();
        nomeCompleto = usuario.getNomeCompleto();
        apelido = usuario.getApelido();
        genero = usuario.getGenero();
        dataNascimento = usuario.getDataNascimento();
        email = usuario.getEmail();
        senha = usuario.getSenha();

        if( apelido.equals("") && senha.equals("") ){
            Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
        }else{
            editor.putInt("id", id);
            editor.putString("nomeCompleto", nomeCompleto);
            editor.putString("apelido", apelido);
            editor.putString("genero", genero);
            editor.putString("dataNascimento", dataNascimento);
            editor.putString("email", email);
            editor.putString("senha", campoSenha.getText().toString());
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void CadastroUsuario(View view){

        Intent intent = new Intent(  LoginActivity.this,CadastroActivity.class);
        startActivity(intent);
        this.finish();
    }
}
