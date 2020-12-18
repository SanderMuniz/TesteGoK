package br.com.sander.testegok.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.sander.testegok.R
import br.com.sander.testegok.repository.HomeRepository
import br.com.sander.testegok.ui.recyclerView.adapter.ListaProductsAdapter
import br.com.sander.testegok.ui.recyclerView.adapter.ListaSpotlightAdapter
import br.com.sander.testegok.ui.viewModel.HomeViewModel
import br.com.sander.testegok.ui.viewModel.factory.HomeViewModelFactory
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lista_produtos.*

class HomeActivity : AppCompatActivity() {

    private val adapterSpotlight by lazy {
        ListaSpotlightAdapter(context = this)
    }

    private val adapterProducts by lazy {
        ListaProductsAdapter(context = this)
    }

    private val viewModel by lazy {
        val repository = HomeRepository()
        val factory = HomeViewModelFactory(repository)
        val provedor = ViewModelProviders.of(this, factory)
        provedor.get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produtos)
        setTitle("Teste Go.K")


        configuraRecycler()

        viewModel.buscaTodos().observe(this, Observer { resource ->

            resource?.dado?.spotlight?.let { spotlight ->
                Log.i("vinculando", "onCreate: vinculando...spotlight")
                adapterSpotlight.atualiza(spotlight)
            }

            resource?.dado?.cash?.let {

                cash_title.text = it.title

                Picasso.get()
                    .load(it.bannerURL)
                    .into(cash_banner, object : Callback {
                        override fun onSuccess() {
                            Log.d("load_url", "success")
                        }

                        override fun onError(e: Exception?) {
                            Log.d("load_url", "error")
                        }
                    })
                Log.i("consumer_api", "onCreate: ${it.title} - ${it.description}")
            }

            resource?.dado?.products?.let {
                adapterProducts.atualiza(it)
            }

        })
    }

    private fun configuraRecycler() {
        val divisor = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        activity_lista_spotlight.addItemDecoration(divisor)
        activity_lista_spotlight.adapter = adapterSpotlight

        activity_lista_products.addItemDecoration(divisor)
        activity_lista_products.adapter = adapterProducts
    }

}