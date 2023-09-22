package com.example.teste

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TelaPrincipal : AppCompatActivity(){
    private lateinit var tentativas: TextView
    private lateinit var pares: TextView
    private lateinit var btnGo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_principal)

        this.tentativas = findViewById(R.id.tentativas)
        this.pares = findViewById(R.id.pares)
        this.btnGo = findViewById(R.id.button)

        this.btnGo.setOnClickListener{

            val intentMain = Intent(this,MainActivity::class.java);
            intentMain.putExtra("tentativas", tentativas.toString())
            intentMain.putExtra("pares", pares.toString())
            startActivity(intentMain)

        }
    }
}