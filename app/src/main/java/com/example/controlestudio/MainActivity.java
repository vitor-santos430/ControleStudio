package com.example.controlestudio;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.controlestudio.bancodedados.BancoDeDados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;

    private Boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClicked();
            }
        });

        FloatingActionButton fabReceita = findViewById(R.id.fabReceita);
        final Intent ActivityAddReceita = new Intent(this, CriarReceita.class);

        fabReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Página Receita", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(ActivityAddReceita);
            }
        });

        FloatingActionButton fabDespesa = findViewById(R.id.fabDespesa);
        final Intent ActivityAddDespesa = new Intent(this, CriarGasto.class);

        fabDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Página Despesa", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(ActivityAddDespesa);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_geralVision)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onAddButtonClicked(){
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked ? true : false;
    }

    public void setVisibility(Boolean clicked){
        if(!clicked){
            findViewById(R.id.fabReceita).animate().alpha(1.0f);
            findViewById(R.id.fabDespesa).animate().alpha(1.0f);
        }else{
            findViewById(R.id.fabReceita).animate().alpha(0.0f);
            findViewById(R.id.fabDespesa).animate().alpha(0.0f);
        }
    }

    public void setAnimation(Boolean clicked){
        rotateOpen  = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose =  AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottom =  AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom  =  AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        if(!clicked){
            findViewById(R.id.fabReceita).startAnimation(fromBottom);
            findViewById(R.id.fabDespesa).startAnimation(fromBottom);
            findViewById(R.id.fab).startAnimation(rotateOpen);
        }else{
            findViewById(R.id.fabReceita).startAnimation(toBottom);
            findViewById(R.id.fabDespesa).startAnimation(toBottom);
            findViewById(R.id.fab).startAnimation(rotateClose);
        }
    }

}
