package com.example.proyectoisa_5.java;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoisa_5.R;

public class CartaRapida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_cartarapida);
    }

    public void entrar(View view) {
        startActivity(new Intent(getApplicationContext(), home.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.itemHome:
                startActivity(new Intent(getApplicationContext(), home.class));
                finish();
                return true;
            case R.id.itemLogin:
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
                return true;
            case R.id.itemRegistro:
                startActivity(new Intent(getApplicationContext(), registro.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}