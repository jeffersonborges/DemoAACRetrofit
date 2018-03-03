package br.com.jborges.demoaacretrofit.repositories

import android.arch.lifecycle.LiveData
import br.com.jborges.demoaacretrofit.entities.EnderecoResponse

interface EnderecoRepository{

    fun buscarEndereco(cep: String): LiveData<EnderecoResponse>
}