package com.goodaplikasi.nisaardiyanti.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.goodaplikasi.nisaardiyanti.models.JobToSave
import com.goodaplikasi.nisaardiyanti.repository.RemoteJobRepository
import kotlinx.coroutines.launch


class RemoteJobViewModel(
    app: Application,
    private val remoteJobRepository: RemoteJobRepository
) : AndroidViewModel(app) {

    fun remoteJobResult() =
    //No.2 ViewModel (live data) terdapat pada baris baris 18-25
        remoteJobRepository.getRemoteJobResponseLiveData()
//mengambil RemoteJobResponse menggunakan livedata untuk mengambil data dari RemoteJobRepository
//Fitur View (data-binding) ada di class RemoteJobViewModel  baris kode 18-25
    fun searchJob(query: String?) =
        remoteJobRepository.searchRemoteJob(query)
//function cari menggunakan query string pada RemoteJobRepository
    fun searchResult() = remoteJobRepository.getSearchJobResponseLiveData()
//function getSearchJobResponseLiveData untuk mengambil data dari remoteJobRepository
 //Viewmodel terletak di kelas Viewmodel pada baris 27-34
    fun insertJob(job: JobToSave) = viewModelScope.launch {
            //function untuk memasukkan job

        remoteJobRepository.insertJob(job)
    }

    fun deleteJob(job: JobToSave) = viewModelScope.launch {
        remoteJobRepository.deleteJob(job)
  //function deleteJob untuk deleteJob

    }

    fun getAllJob() = remoteJobRepository.getAllJobs()
    //function mengambil data Job

}
