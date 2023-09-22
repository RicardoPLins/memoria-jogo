package com.example.teste

class JogoDaMemoria(private var numPares: Int, private var numTentativas: Int) {
    private val cartas = arrayListOf<Carta>()
    private var paresEncontrados = 0


    init {
        val imageIds = (1..numPares).toList().shuffled()
        val totalCartas = numPares * 2
        repeat(totalCartas) {
            val imageId = if (it < numPares) imageIds[it] else imageIds[it - numPares]
            cartas.add(Carta(imageId))
        }
        cartas.shuffle()
    }

    fun ganhou() = paresEncontrados == cartas.size / 2
    fun perdeu() = numTentativas == 0

    fun jogada(carta1: Carta, carta2: Carta) {
            if (carta1.id == carta2.id) {
                carta1.encontrada = true
                carta2.encontrada = true
                paresEncontrados++
                numTentativas--
            } else {
                carta1.virada = false
                carta2.virada = false
                numTentativas--
            }

    }


    fun getCartas() = cartas
}


