package br.com.sander.testegok.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.sander.testegok.model.PayLoad
import br.com.sander.testegok.retrofit.webclient.ProdutoWebClient

class HomeRepository(
    private val webclient: ProdutoWebClient = ProdutoWebClient()
) {

    private val produtosRecebidos = MutableLiveData<Resource<PayLoad>?>()


    fun buscaTodos(): LiveData<Resource<PayLoad>?> {

        val atualizaListaProdutos: (PayLoad) -> Unit = {
            produtosRecebidos.value = Resource(dado = it)
        }

        buscaNaApi(quandoSucesso = atualizaListaProdutos, quandoFalha = { erro ->
            val resourceAtual = produtosRecebidos.value
            val resouceDeFalha = criaResourceDeFalha(resourceAtual, erro)
            produtosRecebidos.value = resouceDeFalha
        })

        return produtosRecebidos
    }

    private fun buscaNaApi(
        quandoSucesso: (PayLoad) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        webclient.buscaTodos(
            quandoSucesso = { payload ->
                payload?.let(quandoSucesso)
            },
            quandoFalha = quandoFalha
        )
    }
}