package com.example.ladm_u4_ejercicio1_sms

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ladm_u4_ejercicio1_sms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val permiso = 1
    val permiso2 = 2 //permiso de mensaje que va llegando

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            //entra si no esta otorgado
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),permiso)
            }else{
                envioSMS()
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==permiso){
            envioSMS()
        }
    }

    private fun envioSMS() {
        SmsManager.getDefault().sendTextMessage("5556",null,"Hola mundo! mi primer mensaje mandado",null,null) // los ultimos 2 parametros son de confirmacion de lectura
              Toast.makeText(this,"Mensaje Enviado",Toast.LENGTH_SHORT).show()
    }
}