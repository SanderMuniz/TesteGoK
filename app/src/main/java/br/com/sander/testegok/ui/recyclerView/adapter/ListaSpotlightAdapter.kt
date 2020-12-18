package br.com.sander.testegok.ui.recyclerView.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.sander.testegok.R
import br.com.sander.testegok.model.Spotlight
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_spotlight.view.*

class ListaSpotlightAdapter(
    private val context: Context,
    private val spotlights: MutableList<Spotlight> = mutableListOf()
) : RecyclerView.Adapter<ListaSpotlightAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.item_spotlight,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    override fun getItemCount() = spotlights.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spotlight = spotlights[position]
        holder.vincula(spotlight)
    }

    fun atualiza(noticias: List<Spotlight>) {
        notifyItemRangeRemoved(0, this.spotlights.size)
        this.spotlights.clear()
        this.spotlights.addAll(noticias)
        notifyItemRangeInserted(0, this.spotlights.size)
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var spotlight: Spotlight


        fun vincula(spotlight: Spotlight) {
            this.spotlight = spotlight

            Picasso.get()
                .load(spotlight.bannerURL)
                .into(itemView.item_banner, object : Callback {
                    override fun onSuccess() {
                        Log.d("load_url", "success")
                    }

                    override fun onError(e: Exception?) {
                        Log.d("load_url", "error $e")
                    }
                })
        }
    }

}