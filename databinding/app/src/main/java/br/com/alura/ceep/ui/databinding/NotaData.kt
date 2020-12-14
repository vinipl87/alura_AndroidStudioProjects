package br.com.alura.ceep.ui.databinding

import androidx.lifecycle.MutableLiveData
import br.com.alura.ceep.model.Nota

class NotaData(
    private var nota: Nota = Nota(),
    val titulo: MutableLiveData<String> = MutableLiveData<String>().also { it.value = nota.titulo},
    val descricao: MutableLiveData<String> = MutableLiveData<String>().also { it.value = nota.descricao },
    val favorita: MutableLiveData<Boolean> = MutableLiveData<Boolean>().also { it.value = nota.favorita },
    val imagemUrl: MutableLiveData<String> = MutableLiveData<String>().also { it.value = nota.imagemUrl }
) {

    /*val temUrl: LiveData<Boolean> =
        Transformations.map(imagemUrl) { url ->
            url.isNotEmpty()
        }*/

    fun atualiza(nota: Nota) {
        this.nota = nota
        titulo.postValue(this.nota.titulo)
        descricao.postValue(this.nota.descricao)
        favorita.postValue(this.nota.favorita)
        imagemUrl.postValue(this.nota.imagemUrl)
    }

    fun paraNota(): Nota? {
        return this.nota.copy(
            titulo = titulo.value ?: return null,
            descricao = descricao.value ?: return null,
            favorita = favorita.value ?: return null,
            imagemUrl = imagemUrl.value ?: return null
        )
    }
}

/*
* class NotaData(
    private var nota: Nota = Nota(),
    val titulo: ObservableField<String> = ObservableField(nota.titulo),
    val descricao: ObservableField<String> = ObservableField(nota.descricao),
    val favorita: ObservableBoolean = ObservableBoolean(nota.favorita),
    val imagemUrl: ObservableField<String> = ObservableField(nota.imagemUrl)
    ) {

    fun atualiza(nota: Nota) {
        this.nota = nota
        titulo.set(this.nota.titulo)
        descricao.set(this.nota.descricao)
        favorita.set(this.nota.favorita)
        imagemUrl.set(this.nota.imagemUrl)
    }

    fun paraNota(): Nota? {
        return this.nota.copy(
            titulo = titulo.get() ?: return null,
            descricao = descricao.get() ?: return null,
            favorita = favorita.get(),
            imagemUrl = imagemUrl.get() ?: return null
        )
    }
}
* */