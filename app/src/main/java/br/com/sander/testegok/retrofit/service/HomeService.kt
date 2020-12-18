package br.com.sander.testegok.retrofit.service

import br.com.sander.testegok.model.PayLoad
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {

    @GET("products")
    fun buscaTodos(): Call<PayLoad>
}