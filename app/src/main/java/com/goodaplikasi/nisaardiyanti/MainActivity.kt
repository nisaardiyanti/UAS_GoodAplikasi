package com.goodaplikasi.nisaardiyanti

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.goodaplikasi.nisaardiyanti.databinding.ActivityMainBinding
import com.goodaplikasi.nisaardiyanti.db.RemoteJobDatabase
import com.goodaplikasi.nisaardiyanti.repository.RemoteJobRepository
import com.goodaplikasi.nisaardiyanti.viewmodel.RemoteJobViewModel
import com.goodaplikasi.nisaardiyanti.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RemoteJobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        setUpViewModel()

    }


    private fun setUpViewModel() {

        val remoteJobRepository = RemoteJobRepository(
            RemoteJobDatabase(this)
        )

        val viewModelProviderFactory =
            RemoteJobViewModelFactory(
                application,
                remoteJobRepository
            )

        viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(RemoteJobViewModel::class.java)

    }
}