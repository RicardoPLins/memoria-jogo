package com.example.teste
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView


class CartaAuxiliar(private val context: Context, private val cartas: List<Carta>) : BaseAdapter() {
    private val cartasEscolhidas = mutableListOf<Carta>()

    override fun getCount() = cartas.size

    override fun getItem(posicao: Int) = cartas[posicao]

    override fun getItemId(posicao: Int) = posicao.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val card = cartas[position]
        val cardView = convertView ?: LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)

        val cardImage = cardView.findViewById<ImageView>(R.id.cardImage)
        val imageResource = if (card.virada || card.encontrada) {
            getImageResource(card.id)
        } else {
            R.drawable.card_back
        }

        cardImage.setImageResource(imageResource)

        return cardView
    }


    // Método auxiliar para obter o recurso de imagem com base no ID
    private fun getImageResource(id: Int): Int {
        return when (id) {
            1 -> R.drawable.time1
            2 -> R.drawable.time2
            3 -> R.drawable.time3
            4 -> R.drawable.time4
            5 -> R.drawable.time5
            6 -> R.drawable.time6
            7 -> R.drawable.time7
            8 -> R.drawable.time8

            // Adicione mais casos conforme necessário para seus IDs de imagem
            else -> R.drawable.bola // Imagem padrão caso o ID não corresponda a nenhuma imagem
        }
    }


    fun addDuplaDeCartas(carta: Carta) {
        if (cartasEscolhidas.size < 2) {
            cartasEscolhidas.add(carta)
        }
    }

    fun getDuplaDeCartas() = cartasEscolhidas

    fun limparDuplaDeCartas() {
        cartasEscolhidas.clear()
    }

}
