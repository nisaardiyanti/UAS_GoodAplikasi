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
//No.1 View (data-binding) ada di class MainActivity  baris kode 14-29
// Fitur View (data-binding) ada di class MainActivity  baris kode 14-29
//variabel binding dengan Lateinit sebagai di-inisiasi nanti.
//lateinit ini digunakan saat kita tidak bisa memasukkan nilai secara langsung,
// melainkan hanya menampung terlebih dahulu yang nantinya bisa diisi null atau non-null
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RemoteJobViewModel
//variabel viewmodel RemoteJobViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
 //SetContentView digunakan untuk mengisi jendela dengan UI yang
    // disediakan dari file layout yang berisi setContentView(R.layout.somae_file).
        setUpViewModel()

    }


    private fun setUpViewModel() {
        //function setupviewmodel
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
        //mengambil data pada remoteviewmodel kelas java
    }
}
