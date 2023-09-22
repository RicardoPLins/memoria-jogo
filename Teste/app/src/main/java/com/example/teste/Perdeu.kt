package com.example.teste

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Perdeu : AppCompatActivity(){
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_perdeu)

        this.btnVoltar = findViewById(R.id.btn_back2)

        this.btnVoltar.setOnClickListener{
            val intentMain = Intent(this,MainActivity::class.java);
            startActivity(intentMain)
        }
    }


}