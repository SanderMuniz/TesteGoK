package br.com.sander.testegok.retrofit.webclient

import br.com.sander.testegok.model.PayLoad
import br.com.sander.testegok.retrofit.AppRetrofit
import br.com.sander.testegok.retrofit.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val PROBLEMA_NA_REQUISICAO = "Houve um problema na requisição!"

class ProdutoWebClient(
    private val service: HomeService = AppRetrofit().produtoService
){

    private fun <T> executaRequisicao(
        call: Call<T>,
        quandoSucesso: (noticiasNovas: T?) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    quandoSucesso(response.body())
                } else {
                    quandoFalha(PROBLEMA_NA_REQUISICAO)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                quandoFalha(t.message)
            }
        })
    }

    fun buscaTodos(
        quandoSucesso: (payLoad: PayLoad?) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        executaRequisicao(
            service.buscaTodos(),
            quandoSucesso,
            quandoFalha
        )
    }

}