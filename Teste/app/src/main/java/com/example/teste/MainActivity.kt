package com.example.teste

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var jogoDaMemoria: JogoDaMemoria
    private lateinit var cartaAuxiliar: CartaAuxiliar
    private lateinit var gridView: GridView
    private lateinit var textView: TextView
    private lateinit var textTentativas: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var tentativas = intent.getStringExtra("tentativas")
//        var pares = intent.getStringExtra("pares")
        this.textView = findViewById(R.id.textView2)
        this.textTentativas = findViewById(R.id.textView)
        //Mude aqui os parâmetros do jogo
        var numTentativas = 10
        val numPares = 4
        jogoDaMemoria = JogoDaMemoria(numPares, numTentativas)

        val cartas = jogoDaMemoria.getCartas()

        textTentativas.text = "Tentativas: " + numTentativas.toString()
        textView.text = "Boa Sorte!"
        cartaAuxiliar = CartaAuxiliar(this, cartas)
        gridView = findViewById<GridView>(R.id.imageView)
        gridView.adapter = cartaAuxiliar


        gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, posicao, id ->
                val carta = cartas[posicao]

                if (!carta.virada && !carta.encontrada) {
                    carta.virada = true
                    cartaAuxiliar.notifyDataSetChanged()
                    cartaAuxiliar.addDuplaDeCartas(carta)
                    if (cartaAuxiliar.getDuplaDeCartas().size == 2) {
                        val selectedCards = cartaAuxiliar.getDuplaDeCartas()
                        val carta1 = selectedCards[0]
                        val carta2 = selectedCards[1]

                        textTentativas.text = numTentativas.toString()
                        jogoDaMemoria.jogada(carta1, carta2)
                        if (carta1.id == carta2.id) {
                            textView.text = "Acertou!"
                            numTentativas--
//                            Toast.makeText(this, "Acertou!", Toast.LENGTH_LONG)
                            textTentativas.text = numTentativas.toString()

                        } else {
                            textView.text = "Errou"
                            numTentativas--
//                            Toast.makeText(this, "Erroou", Toast.LENGTH_SHORT)
                            cartaAuxiliar.notifyDataSetChanged()
                            textTentativas.text = numTentativas.toString()
                        }
                        if (jogoDaMemoria.ganhou()) {
                            textView.text = "Campeão"
                            val intent = Intent(this, Ganhou::class.java)
                            startActivity(intent)
                        }
                        else if (jogoDaMemoria.perdeu()){
                            textView.text = "Perdeu!"
                            val intent2 = Intent(this, Perdeu::class.java)
                            startActivity(intent2)
                        }
                        cartaAuxiliar.notifyDataSetChanged()
                        cartaAuxiliar.limparDuplaDeCartas()
                    }

                }
            }
    }
}