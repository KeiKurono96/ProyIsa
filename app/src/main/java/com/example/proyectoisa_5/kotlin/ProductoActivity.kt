package com.example.proyectoisa_5.kotlin

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.proyectoisa_5.R
import kotlinx.android.synthetic.main.activity_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoActivity : AppCompatActivity() {

    //Creacion de Variables
    private lateinit var database: AppDatabase
    private lateinit var producto: Producto
    private lateinit var productoLiveData: LiveData<Producto>
    private val EDIT_ACTIVITY = 49

    //Crear el producto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        database= AppDatabase.getDatabase(this)

        val idproducto=intent.getIntExtra("id",0)

        val imageUri= ImageController.getImageUri(this,idproducto.toLong())
        imagen_produ.setImageURI(imageUri)

        productoLiveData=database.productos().get(idproducto)

        productoLiveData.observe(this, Observer {
            producto = it

            nombre_producto.text = producto.nombre
            precio_producto.text = "$${producto.precio}"
            detalles_producto.text = producto.descripcion
        })
    }

    //Agregar el menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.producto_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    //Editar y Eliminar el producto
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit_item -> {
                val intent = Intent(this, NuevoProductoActivity::class.java)
                intent.putExtra("producto",producto)
                startActivityForResult(intent, EDIT_ACTIVITY)
            }

            R.id.delete_item -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Eliminar Producto")
                    setMessage("¿Estás seguro de eliminar este producto? Esta acción no se puede deshacer.")
                    setPositiveButton("SI"){ _: DialogInterface, _: Int ->
                        productoLiveData.removeObservers(this@ProductoActivity)

                        CoroutineScope(Dispatchers.IO).launch {
                            database.productos().delete(producto)
                            ImageController.deleteImage(this@ProductoActivity,producto.idProducto.toLong())
                            this@ProductoActivity.finish()
                        }
                    }
                    setNegativeButton("NO",null)
                }.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //Cambiar imagen segun la seleccionada de la galeria
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imagen_produ.setImageURI(data!!.data)
            }
        }
    }
}