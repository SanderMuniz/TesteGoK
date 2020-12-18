package br.com.sander.testegok.ui.recyclerView.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.sander.testegok.R
import br.com.sander.testegok.model.Products
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_spotlight.view.*

class ListaProductsAdapter(
    private val context: Context,
    private val products: MutableList<Products> = mutableListOf()
) : RecyclerView.Adapter<ListaProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.item_products,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    override fun getItemCount() = products.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spotlight = products[position]
        holder.vincula(spotlight)
    }

    fun atualiza(products: List<Products>) {
        notifyItemRangeRemoved(0, this.products.size)
        this.products.clear()
        this.products.addAll(products)
        notifyItemRangeInserted(0, this.products.size)
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var product: Products


        fun vincula(products: Products) {
            this.product = products

            Picasso.get()
                .load(product.imageURL)
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