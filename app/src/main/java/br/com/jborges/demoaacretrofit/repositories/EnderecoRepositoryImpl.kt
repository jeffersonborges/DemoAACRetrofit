package br.com.jborges.demoaacretrofit.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.jborges.demoaacretrofit.api.EnderecoAPI
import br.com.jborges.demoaacretrofit.entities.Endereco
import br.com.jborges.demoaacretrofit.entities.EnderecoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EnderecoRepositoryImpl : EnderecoRepository {

    private val enderecoAPI: EnderecoAPI

    init {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("'https://viacep.com.br")
                .build()

        enderecoAPI = retrofit.create(EnderecoAPI::class.java)
    }

    override fun buscarEndereco(cep: String): LiveData<EnderecoResponse> {

        val liveData = MutableLiveData<EnderecoResponse>()

        enderecoAPI.pesquisar(cep)
                .enqueue(object : Callback<Endereco> {

                    override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {
                        liveData.value = EnderecoResponse(response?.body()!!)
                    }

                    override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                        liveData.value = EnderecoResponse(t!!)
                    }

                })

        return liveData
    }

}