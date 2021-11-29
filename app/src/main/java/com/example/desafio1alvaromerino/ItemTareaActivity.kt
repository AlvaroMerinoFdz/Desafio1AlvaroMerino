package com.example.desafio1alvaromerino


import Modelo.Tarea
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class ItemTareaActivity : AppCompatActivity() {
    lateinit var tarea : Tarea
    private val cameraRequest = 1888
    lateinit var imagen:ImageView
    lateinit var nombreImagen:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_tarea)
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)


        val i = intent.extras
        tarea = i?.getSerializable("Tarea") as Tarea
        imagen = findViewById(R.id.imgImagenTarea)


        var btnGuardar : Button = findViewById(R.id.btnGuardarTarea)
        btnGuardar.setOnClickListener {
            var txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
            var imagen = findViewById<ImageView>(R.id.imgImagenTarea)
            var switch = findViewById<Switch>(R.id.swRealizada)

            tarea.descripcion = txtDescripcion.text.toString()
            tarea.foto = imagen.imageAlpha
            tarea.realizada = switch.isChecked
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == cameraRequest) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
                imagen.setImageBitmap(photo)

                var fotoFichero = File(getExternalFilesDir(null), nombreImagen)
                var uri = Uri.fromFile(fotoFichero)
                var fileOutStream = FileOutputStream(fotoFichero)
                photo.compress(Bitmap.CompressFormat.PNG, 100, fileOutStream);
                fileOutStream.flush();
                fileOutStream.close();
            }
        }catch(e: Exception){
            Log.e("Alvaro",e.toString())
        }
    }


    fun guardar(view:View){
        var txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        var imagen = findViewById<ImageView>(R.id.imgImagenTarea)
        var switch = findViewById<Switch>(R.id.swRealizada)

        tarea.descripcion = txtDescripcion.text.toString()
        tarea.foto = imagen.imageAlpha
        tarea.realizada = switch.isChecked

    }
    fun tomarFoto(view:View){
        val cameraIntent = Intent(ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent,cameraRequest)
    }

}