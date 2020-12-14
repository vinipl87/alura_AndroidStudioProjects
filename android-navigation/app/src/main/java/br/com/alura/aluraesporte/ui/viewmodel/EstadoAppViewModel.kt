package br.com.alura.aluraesporte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EstadoAppViewModel : ViewModel() {

    val componentes: LiveData<ComponentesVisuais> get() = _componentes

    private var _componentes: MutableLiveData<ComponentesVisuais> =
        MutableLiveData<ComponentesVisuais>().also {
            it.value = temComponentes
        }

    var temComponentes: ComponentesVisuais = ComponentesVisuais()
        set(value) {
            field = value
            _componentes.value = value
        }

}

class ComponentesVisuais(
    val appBar: Boolean = false,
    val bottomNavigation: Boolean = false
)

/*
class EstadoAppViewModel : ViewModel() {

    val appBar: LiveData<Boolean> get() = _appBar

    private var _appBar: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().also {
            it.value = temAppBar
        }

    var temAppBar: Boolean = false
        set(value) {
            field = value
            _appBar.value = value
        }


}*/