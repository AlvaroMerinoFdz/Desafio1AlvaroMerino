package com.example.desafio1alvaromerino


import Modelo.Tarea
import Utiles.Auxiliar
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class ItemTareaActivity : AppCompatActivity() {
    private val cameraRequest = 1888
    lateinit var imagen:ImageView
    lateinit var nombreImagen:String
    private var idNota : String? = null
    private var idTarea : String? = null
    lateinit var tarea :Tarea
    lateinit var txtDescripcion: EditText
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    lateinit var switch: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_tarea)
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)

        txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        imagen = findViewById<ImageView>(R.id.imgImagenTarea)
        switch = findViewById(R.id.swRealizado)

        val i = intent.extras

        idNota = i?.getString("idNota")
        idTarea = i?.getString("idTarea")

        //Creamos la tarea
         tarea = Tarea(idTarea!!,idNota!!,"")


        var btnGuardar : Button = findViewById(R.id.btnGuardarTarea)
        btnGuardar.setOnClickListener {

            tarea.descripcion = txtDescripcion.text.toString()
            tarea.realizada = switch.isChecked

            Conexion.Conexion.addTarea(this,tarea)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == cameraRequest) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
                tarea.foto = photo
                imagen.setImageBitmap(photo)
                var fotoFichero = File(getExternalFilesDir(null), nombreImagen)
                var fileOutStream = FileOutputStream(fotoFichero)
                photo.compress(Bitmap.CompressFormat.PNG, 100, fileOutStream);
                fileOutStream.flush();
                fileOutStream.close();
            }
        }catch(e: Exception){
            Log.e("Alvaro",e.toString())
        }
    }


    fun tomarFoto(view:View){
        val cameraIntent = Intent(ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent,cameraRequest)
    }



}