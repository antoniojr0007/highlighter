package br.com.highlighter.www.highlighter.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.highlighter.www.highlighter.Config.Servidor;
import br.com.highlighter.www.highlighter.Fragment.SugestoesFragment;
import br.com.highlighter.www.highlighter.R;

import static android.app.PendingIntent.getActivity;

public class SugestaoLivroActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private  EditText campoTitulo, campoAutor, campoEditora;
    private  Button buttonCancelar, buttonEnviar;
    ProgressDialog progresso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestao_livro);

        campoTitulo = findViewById(R.id.editTextTitulo);
        campoAutor = findViewById(R.id.editTextAutor);
        campoEditora = findViewById(R.id.editTextEditora);
        buttonCancelar = findViewById(R.id.buttonCancelar);
        buttonEnviar = findViewById(R.id.buttonEnviar);

        request = Volley.newRequestQueue(this);

    }

    public void CancelarSugestao (View view){
        LimparTela();
        Intent intent = new Intent( SugestaoLivroActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
    private void TelaPrincipal(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void carregarWebService(View view) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        progresso = new ProgressDialog(this);
        progresso.setMessage("Aguarde um momento...");
        progresso.show();

        String url = Servidor.mostrarServidor() +"sugestaoLivro.php?nomeLivro="+campoTitulo.getText().toString()+"&autor="+campoAutor.getText().toString()+"&editora="+campoEditora.getText().toString()+"";

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();
        Toast.makeText(this, "Não foi possível conectar ao servidor!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();
        Toast.makeText(this, "Sugestao enviada com sucesso!", Toast.LENGTH_LONG).show();

        LimparTela();
        TelaPrincipal();

    }

    public void LimparTela(){
        campoTitulo.setText("");
        campoAutor.setText("");
        campoEditora.setText("");
    }
}
