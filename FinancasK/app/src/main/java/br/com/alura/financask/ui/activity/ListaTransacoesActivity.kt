package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.financask.R
import br.com.alura.financask.dao.TransacaoDAO
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTransacoesAdapter
import br.com.alura.financask.ui.dialog.AdicionaTransacaoDialog
import br.com.alura.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes

    //private lateinit var viewDaActivity: View
    private val viewDaActivity by lazy {
        window.decorView
    }
    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun remove(posicaoDaTransacao: Int) {
        dao.remove(posicaoDaTransacao)
        atualizaTransacoes()
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        dao.altera(transacao, posicao)
        atualizaTransacoes()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.RECEITA)
            }
        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.DESPESA)
            }
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, posicao, _ ->
                val transacao = transacoes[posicao]
                chamaDialogDeAlteracao(transacao, posicao)
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val idDoMenu = item?.itemId
        if (idDoMenu == 1) {
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoDaTransacao = adapterMenuInfo.position
            remove(posicaoDaTransacao)
        }
        return super.onContextItemSelected(item)
    }

    /*private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    adiciona(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }*/

    //HOF verbose
    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            .chama(tipo, delegate = { transacaoCriada ->
                adiciona(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
            })
    }

    //HOF
    private fun chamaDialogDeAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
            .chama(transacao) {
                altera(it, posicao)
            }
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }
}