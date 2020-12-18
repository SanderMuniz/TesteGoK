package br.com.sander.testegok.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.sander.testegok.model.PayLoad
import br.com.sander.testegok.repository.HomeRepository
import br.com.sander.testegok.repository.Resource

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    fun buscaTodos(): LiveData<Resource<PayLoad>?> {
        return repository.buscaTodos()
    }
}