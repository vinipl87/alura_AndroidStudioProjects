package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.extensions.formatParaMoedaBrasileira
import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.ui.activity.CHAVE_PRODUTO_ID
import br.com.alura.aluraesporte.ui.viewmodel.DetalhesProdutoViewModel
import kotlinx.android.synthetic.main.detalhes_produto.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesProdutoFragment : Fragment() {

    private val argumentos by navArgs<DetalhesProdutoFragmentArgs>()
    private val produtoId by lazy {
        //arguments?.getLong(CHAVE_PRODUTO_ID)?: throw IllegalArgumentException(ID_PRODUTO_INVALIDO)
        argumentos.produtoId
    }
    private val viewModel: DetalhesProdutoViewModel by viewModel { parametersOf(produtoId) }
    private val controlador by lazy { findNavController() }
    //var quandoProdutoComprado: (produto: Produto) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.detalhes_produto,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaProduto()
        configuraBotaoComprar()
    }

    private fun configuraBotaoComprar() {
        detalhes_produto_botao_comprar.setOnClickListener {
            //viewModel.produtoEncontrado.value?.let(quandoProdutoComprado)
            viewModel.produtoEncontrado.value?.let {
                vaiParaPagamento()
            }
        }
    }

    private fun vaiParaPagamento() {
        val dados = Bundle()
        dados.putLong(CHAVE_PRODUTO_ID, produtoId)
        controlador.navigate(R.id.acao_detalhesProduto_para_pagamento, dados)
    }

    private fun buscaProduto() {
        viewModel.produtoEncontrado.observe(this, Observer {
            it?.let { produto ->
                detalhes_produto_nome.text = produto.nome
                detalhes_produto_preco.text = produto.preco.formatParaMoedaBrasileira()
            }
        })
    }

}