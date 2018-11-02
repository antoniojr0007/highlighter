package br.com.highlighter.www.highlighter.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.highlighter.www.highlighter.Config.MaskEditUtil;
import br.com.highlighter.www.highlighter.Config.Servidor;
import br.com.highlighter.www.highlighter.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class CadastroActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    String foto = "fotinho.jpg";
    String senhaCriptografada = "";

    Spinner campoGenero;
    private EditText campoNomeCompleto, campoApelido, campoDataNasc, campoTelefone, campoEmail, campoSenha;
    private Spinner spinnerGenero;
    private CircleImageView imagemPerfil;
    private Button buttonSalvar, buttonCancelar;
    ProgressDialog progresso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNomeCompleto = findViewById(R.id.editNome);
        campoApelido      = findViewById(R.id.editApelido);
        campoGenero       = findViewById(R.id.spinnerGenero);
        campoDataNasc     = findViewById(R.id.editDataNasc);
        campoDataNasc.addTextChangedListener(MaskEditUtil.mask(campoDataNasc, MaskEditUtil.FORMAT_DATE));
        campoTelefone     = findViewById(R.id.editTelefone);
        campoTelefone.addTextChangedListener(MaskEditUtil.mask(campoTelefone, MaskEditUtil.FORMAT_FONE));
        campoEmail        = findViewById(R.id.editEmail);
        campoSenha        = findViewById(R.id.editSenha);
        buttonSalvar      = findViewById(R.id.buttonSalvar);
        buttonCancelar    = findViewById(R.id.buttonCancelar);

        request = Volley.newRequestQueue(this);
    }

    // Voltar ao menu principal.
    private void voltarTelaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void carregarWebService(View view) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        progresso = new ProgressDialog(this);
        progresso.setMessage("Aguarde um momento...");
        progresso.show();

        converterSenha();

        String url = Servidor.mostrarServidor() +"Usuario.php?nomeCompleto="+campoNomeCompleto.getText().toString()+"&apelido="+campoApelido.getText().toString()+"&dataNascimento="+campoDataNasc.getText().toString()+"&telefone="+campoTelefone.getText().toString()+"&email="+campoEmail.getText().toString()+"&senha="+senhaCriptografada+"&genero="+campoGenero.getSelectedItem().toString()+"&imagem="+foto+"";

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
        Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();

        LimparTela();
        voltarTelaLogin();
    }

    public void CancelarCadastro (View view){
        LimparTela();
        Intent intent = new Intent( CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();

    }

    public void LimparTela(){
        campoGenero.setSelection(0);
        campoSenha.setText("");
        campoEmail.setText("");
        campoDataNasc.setText("");
        campoApelido.setText("");
        campoNomeCompleto.setText("");

    }
}