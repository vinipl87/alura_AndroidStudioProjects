package br.com.alura.technews.repository

class Resource<T>(
    val dado: T?,
    val erro: String? = null
)
/*
fun <T> criaResourceDeFalha(
    resourceAtual: Resource<T?>?,
    erro: String?
): Resource<T?> {
    if (resourceAtual != null) {
        return Resource(dado = resourceAtual.dado, erro = erro)
    }
    return Resource(dado = null, erro = erro)
}*/
/*
open class Resource<T>(
    val dado: T?,
    val erro: String? = null
)

class SucessoResource<T>(dado: T) : Resource<T>(dado)

class FalhaResourc<T>(erro: String) : Resource<T>(dado = null, erro = erro)

 */