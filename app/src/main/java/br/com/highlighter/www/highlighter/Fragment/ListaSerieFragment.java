package br.com.highlighter.www.highlighter.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

import br.com.highlighter.www.highlighter.Adapter.AdapterSeries;
import br.com.highlighter.www.highlighter.Config.Servidor;
import br.com.highlighter.www.highlighter.Model.Serie;
import br.com.highlighter.www.highlighter.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaSerieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaSerieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaSerieFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Serie serie;
    RecyclerView recyclerSeries;
    ArrayList<Serie> listaSeries;
    ProgressDialog progresso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    private OnFragmentInteractionListener mListener;

    public ListaSerieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaSerieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaSerieFragment newInstance(String param1, String param2) {
        ListaSerieFragment fragment = new ListaSerieFragment();
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

        View view = inflater.inflate(R.layout.fragment_lista_serie, container, false);

        listaSeries = new ArrayList<>();

        recyclerSeries = view.findViewById(R.id.recyclerSerie);
        recyclerSeries.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerSeries.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());

        carregarWebService();

        return view;
    }

    private void carregarWebService() {
        progresso = new ProgressDialog(getContext());
        progresso.setMessage("Listando series...");
        progresso.show();

        //String url = Servidor.mostrarServidor() +"listarSeries.php";
        String url = Servidor.ConsultaSerie();

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
        Toast.makeText(getContext(), "Não foi possível conectar ao servidor!", Toast.LENGTH_LONG).show();
        Log.v("String", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        progresso.hide();

        Serie serie = null;
        JSONArray json = response.optJSONArray("serie");

        try {
            for(int i = 0; i < json.length(); i++){
                serie = new Serie();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                serie.setNome_Serie(jsonObject.optString("nomeLivro"));

                listaSeries.add(serie);
            }

            progresso.hide();
            AdapterSeries adapter = new AdapterSeries(listaSeries);
            recyclerSeries.setAdapter(adapter);
        }catch (JSONException e){
            Toast.makeText(getContext(), "Não foi possível conectar ao servidor!" + response, Toast.LENGTH_LONG).show();
        }

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
