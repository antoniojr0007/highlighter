package br.com.highlighter.www.highlighter.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.highlighter.www.highlighter.Fragment.BemVindoFragment;
import br.com.highlighter.www.highlighter.Fragment.ContatoFragment;
import br.com.highlighter.www.highlighter.Fragment.DesenvolvedorFragment;
import br.com.highlighter.www.highlighter.Fragment.ListaFilmeFragment;
import br.com.highlighter.www.highlighter.Fragment.ListaLivroFragment;
import br.com.highlighter.www.highlighter.Fragment.PerfilFragment;
import br.com.highlighter.www.highlighter.Fragment.ListaSerieFragment;
import br.com.highlighter.www.highlighter.Fragment.SobreFragment;
import br.com.highlighter.www.highlighter.Fragment.SugestoesFragment;
import br.com.highlighter.www.highlighter.Interface.Ifragment;
import br.com.highlighter.www.highlighter.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Ifragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("autenticar", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String nomeCompleto = sharedPreferences.getString("nomeCompleto", "");
        String email = sharedPreferences.getString("email", "");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment myFragment = new BemVindoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);

        TextView headerNomeCompleto = hView.findViewById(R.id.TextViewNomeCompleto);
        TextView headerEmail =  hView.findViewById(R.id.textViewEmail);

        headerNomeCompleto.setText(nomeCompleto);
        headerEmail.setText(email);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent Activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Perfil) {
            Fragment fragment = new Fragment();
            fragment.getActivity();
            Intent intent = new Intent(this, fragment.getActivity().getClass());
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_Sair) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment myFragment = null;
        boolean fragmentSelect = false;

        if (id == R.id.nav_bem_vindo) {
            myFragment = new BemVindoFragment();
            fragmentSelect = true;
        } else if (id == R.id.nav_livro) {
            myFragment = new ListaLivroFragment();
            fragmentSelect = true;
        }else if (id == R.id.nav_filme) {
            myFragment = new ListaFilmeFragment();
            fragmentSelect = true;
        }else if (id == R.id.nav_serie) {
            myFragment = new ListaSerieFragment();
            fragmentSelect = true;
        } else if (id == R.id.nav_sugestoes) {
            myFragment = new SugestoesFragment();
            fragmentSelect = true;
        } else if (id == R.id.nav_contato) {
            myFragment = new ContatoFragment();
            fragmentSelect = true;
        }else if (id == R.id.nav_Perfil) {
            myFragment = new PerfilFragment();
            fragmentSelect = true;
        } else if (id == R.id.nav_Sobre) {
            myFragment = new SobreFragment();
            fragmentSelect = true;
        }else if (id == R.id.nav_Desenvolvedor) {
            myFragment = new DesenvolvedorFragment();
            fragmentSelect = true;
        }

        if(fragmentSelect==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
