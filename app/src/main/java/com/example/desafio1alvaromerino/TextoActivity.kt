package com.example.desafio1alvaromerino

import Adaptador.AdaptadorContactos
import Modelo.Contacto
import Modelo.DeTexto
import Modelo.Notas
import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TextoActivity : AppCompatActivity() {
    lateinit var txtAsunto: EditText
    lateinit var txtContenido : EditText
    lateinit var btnGuardar : Button
    lateinit var  nota: DeTexto
    lateinit var adaptadorContactos: Adaptador.AdaptadorContactos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texto)

        val i = intent.extras
        nota = i?.getSerializable("Texto") as DeTexto

        txtAsunto = findViewById(R.id.etAsunto)
        txtContenido = findViewById(R.id.etTexto)
        btnGuardar = findViewById(R.id.btnGuardar)

        txtAsunto.append(nota.asunto)
        txtContenido.setText(nota.texto)

    }
    fun cerrar(view:View){
        finish()
    }
    fun guardar(view:View){
        val id = nota.id
        Conexion.Conexion.modificarNotaTexto(this,id, nota)
        finish()
    }

    fun compartir(view:View){
        var contactos: ArrayList<Contacto>
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            contactos = getContactos()
        } else {
            requestPermission();
            contactos = getContactos()
        }
        abrirDialogContactos(contactos)
    }

    private fun abrirDialogContactos(contactos: java.util.ArrayList<Contacto>) {
        var dialogView = layoutInflater.inflate(R.layout.layout_lista_contactos,null)
        var rvContacto: RecyclerView = dialogView.findViewById(R.id.rvContactos)
        rvContacto.setHasFixedSize(true)
        rvContacto.layoutManager = LinearLayoutManager(this)
        adaptadorContactos = AdaptadorContactos(this,contactos)
        rvContacto.adapter = adaptadorContactos

        if(contactos.size > 0){
            AlertDialog.Builder(this).setTitle(getString(R.string.Contactos))
                .setView(dialogView).setPositiveButton(getString(R.string.OK)){dialog,_ ->
                    if(adaptadorContactos.isSelected()){
                        var contacto = adaptadorContactos.getSelected()
                        enviarMensaje(contacto)
                        dialog.dismiss()
                    }else{
                        Toast.makeText(this,getString(R.string.noSeleccionado),Toast.LENGTH_LONG).show()
                    }
                }.setCancelable(true).create().show()
        }else{
            Toast.makeText(this,getString(R.string.noExisten),Toast.LENGTH_LONG).show()
        }

    }
    private fun enviarMensaje(contacto: Contacto){
        val packageManager = this.packageManager
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY) || packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA)){
            var permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)
            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),101)
            }else{
                sendSMS(contacto)
            }
        }
    }

    private fun getContactos(): ArrayList<Contacto> {
        var contactos:ArrayList<Contacto> = ArrayList()
        var contentResolver = this.contentResolver
        var cur : Cursor? = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null,null)
        if(cur!= null){
            if(cur.count > 0){
                while(cur != null && cur.moveToNext()){
                    var id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID).toInt())
                    var nombre = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME).toInt())
                    if(cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER).toInt())>0){
                        val pCur = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id), null)
                        //obtenemos los numeros de ese contacto
                        if(pCur!!.moveToFirst()){
                            val numeroTfno = pCur!!.getString( pCur!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER).toInt())
                            contactos.add(Contacto(nombre,numeroTfno))
                        }
                        pCur!!.close()
                    }
                }

            }
        }

        cur?.close()
        return contactos
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_CONTACTS
            )
        ) {
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                79
            )
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_CONTACTS
            )
        ) {
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                79
            )
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSMS(adaptadorContactos.getSelected())
            } else {
                Toast.makeText(this, "Necesitas tener permisos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private fun sendSMS(contacto: Contacto){
        var numero :String = contacto.numero
        numero.replace(" ","")
        numero.replace("+34","")
        var miMsg = "${nota.asunto} : \n \t ${nota.texto}"
        if(TextUtils.isDigitsOnly(numero)){
            var smsManager:SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(numero,null,miMsg,null,null)
        }
    }

}