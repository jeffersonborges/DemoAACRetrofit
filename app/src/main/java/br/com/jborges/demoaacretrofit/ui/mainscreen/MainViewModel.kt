package br.com.jborges.demoaacretrofit.ui.mainscreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.jborges.demoaacretrofit.entities.EnderecoResponse
import br.com.jborges.demoaacretrofit.repositories.EnderecoRepository
import br.com.jborges.demoaacretrofit.repositories.EnderecoRepositoryImpl

//AndroidViewModel mexe com biblioteca room
class MainViewModel: ViewModel(){
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    private val enderecoRepository: EnderecoRepository
    private val maApiResponse: MediatorLiveData<EnderecoResponse> =
            MediatorLiveData()

    val apiResponse : LiveData<EnderecoResponse>
    get() = maApiResponse

    init {
        enderecoRepository = EnderecoRepositoryImpl()
    }

    fun pesquisarEndereco(cep: String): LiveData<EnderecoResponse>{
        isLoading.postValue(true)
        maApiResponse.addSource(
                enderecoRepository.buscarEndereco(cep)
        ){
            apiResponse -> maApiResponse.value = apiResponse
            isLoading.postValue(false)
        }
        return maApiResponse
    }
}
