package br.com.highlighter.www.highlighter.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import br.com.highlighter.www.highlighter.Activity.MainActivity;
import br.com.highlighter.www.highlighter.Config.Servidor;
import br.com.highlighter.www.highlighter.Model.Usuario;
import br.com.highlighter.www.highlighter.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContatoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContatoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContatoFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String nome = "";
    String email = "";
    String sujectEmail = "antonio.250291@gmail.com";

    EditText campoNomeCompleto, campoEmail, campoAssunto, campoCorpoEmail;
    Button buttonCancelar, buttonEnviar ;

    ProgressDialog progresso;
    LinearLayout layoutContato;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private OnFragmentInteractionListener mListener;

    public ContatoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContatoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContatoFragment newInstance(String param1, String param2) {
        ContatoFragment fragment = new ContatoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contato, container, false);

        campoNomeCompleto = view.findViewById(R.id.editTextNomeCompleto);
        campoEmail        = view.findViewById(R.id.editEmail);
        campoAssunto      = view.findViewById(R.id.editTextAssunto);
        campoCorpoEmail   = view.findViewById(R.id.editTextCorpoEmail);

        campoNomeCompleto.setTextColor(Color.BLACK);
        campoEmail.setTextColor(Color.BLACK);
        campoAssunto.setTextColor(Color.BLACK);
        campoCorpoEmail.setTextColor(Color.BLACK);

        request = Volley.newRequestQueue(getContext());

        carregarWebService();

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = campoEmail.getText().toString();
                //String subject = sujectEmail.getText().toString();
                String message = campoAssunto.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                //email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));


            }
        });

        return view;
    }

    public void carregarWebService(){
        progresso = new ProgressDialog(getContext());
        progresso.setMessage("Buscando...");
        progresso.show();

        sharedPreferences = getContext().getSharedPreferences("autenticar", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Integer id = sharedPreferences.getInt("id", 0);
        nome = sharedPreferences.getString("nome", "");
        email= sharedPreferences.getString("email","");


        String url = Servidor.mostrarServidor() +"consultarUsuario.php?id="+id;

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();
        Usuario usuario = new Usuario();
        JSONArray jsonArray = response.optJSONArray("usuario");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setNomeCompleto(jsonObject.optString("nomeCompleto"));
            usuario.setEmail(jsonObject.optString("email"));

        }catch (JSONException e){
            e.printStackTrace();
        }

        campoNomeCompleto.setText(usuario.getNomeCompleto());
        campoEmail.setText(usuario.getEmail());

    }

    private void LimpaCampos(){

        campoNomeCompleto.setText("");
        campoEmail.setText("");
        campoAssunto.setText("");
        campoCorpoEmail.setText("");
    }

    private void CancelarContato(){
        LimpaCampos();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
