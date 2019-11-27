package com.carlesramos.practicagestionmaildef;

import android.content.Intent;
import android.os.Bundle;

import com.carlesramos.practicagestionmaildef.fragments.FragmentListadoEmails;
import com.carlesramos.practicagestionmaildef.interficies.IMailListener;
import com.carlesramos.practicagestionmaildef.parsers.DataParser;
import com.germangascon.practicagestionmaildef.R;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMailListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DataParser parser = new DataParser(this);
        parser.parse();
    }

    @Override
    public void onBackPressed() {
        /**
         * Si el usuario pulsa el botón atrás mientras está mostrándose el menú del NavigationView,
         * hacemos que se cierre dicho menú, ya que el comportamiento por defecto es cerrar la
         * Activity.
         */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el menú de la ActionBar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se ha hecho click en algún item del menú de la ActionBar
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentListadoEmails f;
        // Se ha hecho click en algún item del NavigationView
        int id = item.getItemId();

        if (id == R.id.nav_recibidos) {
            f = new FragmentListadoEmails(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            f.setMailListener(this);
            setTitle("Recibidos");
        } else if (id == R.id.nav_enviados) {
            f = new FragmentListadoEmails(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            f.setMailListener(this);
            setTitle("Enviados");
        } else if (id == R.id.nav_spam) {
            f = new FragmentListadoEmails(item);
            f.setMailListener(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Espam");
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMailSelected(int position) {
        Intent i = new Intent(this, DetalleActivity.class);
        startActivity(i);
    }
}
