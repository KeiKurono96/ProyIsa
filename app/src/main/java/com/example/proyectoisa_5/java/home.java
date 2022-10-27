package com.example.proyectoisa_5.java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoisa_5.R;
import com.example.proyectoisa_5.kotlin.MainListaProductos;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_home);

        findViewById(R.id.webBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickweb("https://ejgstacm.lucusvirtual.es/");
            }
        });
    }

    public void lista(View view){
        startActivity(new Intent(getApplicationContext(), MainListaProductos.class));
        finish();
    }

    public void rapida(View view){
        startActivity(new Intent(getApplicationContext(), CartaRapida.class));
        finish();
    }

    public void informacion(View view){
        startActivity(new Intent(getApplicationContext(),informacion.class));
        finish();
    }

    public void mipedido(View view){
        startActivity(new Intent(getApplicationContext(), envio.class));
        finish();
    }

    public void clickweb(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}