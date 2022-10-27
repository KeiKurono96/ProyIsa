package com.example.proyectoisa_5.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.proyectoisa_5.R
import com.example.proyectoisa_5.java.home
import com.example.proyectoisa_5.java.login
import com.example.proyectoisa_5.java.registro
import kotlinx.android.synthetic.main.activity_mainlistaproductos.*

class MainListaProductos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainlistaproductos)

        var listaProductos= emptyList<Producto>()

        val database= AppDatabase.getDatabase(this)

        database.productos().getAll().observe(this, Observer {
            listaProductos=it

            val adapter= ProductosAdapter(this,listaProductos)

            lista.adapter=adapter
        })

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("id",listaProductos[position].idProducto)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NuevoProductoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_inicial,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemHome -> {
                startActivity(Intent(applicationContext, home::class.java))
                finish()
                true
            }
            R.id.itemLogin -> {
                startActivity(Intent(applicationContext, login::class.java))
                finish()
                true
            }
            R.id.itemRegistro -> {
                startActivity(Intent(applicationContext, registro::class.java))
                finish()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}