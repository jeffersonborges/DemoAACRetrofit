package br.com.jborges.demoaacretrofit.ui.mainscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.jborges.demoaacretrofit.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btPesquisar.setOnClickListener{
            mainViewModel.pesquisarEndereco(etCep.text.toString())
        }

        mainViewModel.apiResponse.observe(this, Observer {
            apiResponse ->
            if (apiResponse?.erro == null){
                Log.i("TAG", "SUCESSO")
            } else {
                Log.i("TAG", "ERRO")
            }
        })
    }
}
