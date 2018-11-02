package br.com.highlighter.www.highlighter.Interface;

import br.com.highlighter.www.highlighter.Fragment.BemVindoFragment;
import br.com.highlighter.www.highlighter.Fragment.ContatoFragment;
import br.com.highlighter.www.highlighter.Fragment.DesenvolvedorFragment;
import br.com.highlighter.www.highlighter.Fragment.ListaFilmeFragment;
import br.com.highlighter.www.highlighter.Fragment.ListaLivroFragment;
import br.com.highlighter.www.highlighter.Fragment.ListaSerieFragment;
import br.com.highlighter.www.highlighter.Fragment.PerfilFragment;
import br.com.highlighter.www.highlighter.Fragment.SobreFragment;
import br.com.highlighter.www.highlighter.Fragment.SugestoesFragment;

public interface Ifragment extends
        BemVindoFragment.OnFragmentInteractionListener,
        ContatoFragment.OnFragmentInteractionListener,
        DesenvolvedorFragment.OnFragmentInteractionListener,
        ListaFilmeFragment.OnFragmentInteractionListener,
        ListaLivroFragment.OnFragmentInteractionListener,
        PerfilFragment.OnFragmentInteractionListener,
        ListaSerieFragment.OnFragmentInteractionListener,
        SobreFragment.OnFragmentInteractionListener,
        SugestoesFragment.OnFragmentInteractionListener {

}
