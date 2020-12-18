package br.com.sander.testegok.repository

import br.com.sander.testegok.model.PayLoad

class Resource<T>(
    val dado: T?,
    val erro: String? = null
)


fun criaResourceDeFalha(
    resourceAtual: Resource<PayLoad>?,
    erro: String?
): Resource<PayLoad>? {
    if (resourceAtual != null) {
        return Resource(
            dado = resourceAtual.dado,
            erro = erro
        )
    }
    return Resource(
        dado = null,
        erro = erro
    )

}