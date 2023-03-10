package com.goodaplikasi.nisaardiyanti.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goodaplikasi.nisaardiyanti.api.RetrofitInstance
import com.goodaplikasi.nisaardiyanti.db.RemoteJobDatabase
import com.goodaplikasi.nisaardiyanti.models.JobToSave
import com.goodaplikasi.nisaardiyanti.models.RemoteJob
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteJobRepository(private val db: RemoteJobDatabase) {
//kelas RemoteJobRepository dengan db RemoteJobDatabase
    private val remoteJobService = RetrofitInstance.api
    private val remoteJobResponseLiveData: MutableLiveData<RemoteJob> = MutableLiveData()
    private val searchRemoteJobLiveData: MutableLiveData<RemoteJob> = MutableLiveData()
    init {
        getRemoteJobResponse()
                //mengambil data RemoteJobResponse
    }
     private fun getRemoteJobResponse() {
        remoteJobService.getRemoteJob().enqueue(
                    //mengambil data Remotejob
            object : Callback<RemoteJob> {
                override fun onResponse(call: Call<RemoteJob>, response: Response<RemoteJob>) {
//memanggil RemoteJob response RemoteJob
                    if (response.body() != null) {
                        remoteJobResponseLiveData.postValue(response.body())
   //remoteJobResponse untuk LiveData
                }  }
               // Repository yang berisi host terdapat  pada kelas RemoteJobRepository.kt  baris 35-37
 //no.6 repository(apakah online first) pada baris 35-37
                override fun onFailure(call: Call<RemoteJob>, t: Throwable) {
                    remoteJobResponseLiveData.postValue(null)
                    Log.d("error ibm", t.message.toString())
//Fitur Repository(apakah online first)  Tedapat pada Class RemoteJobRepository baris 35-37
                }
            })
    }


    fun searchRemoteJob(query: String?) {
        remoteJobService.searchRemoteJob(query).enqueue(
            object : Callback<RemoteJob> {
                override fun onResponse(call: Call<RemoteJob>, response: Response<RemoteJob>) {
                    if (response.body() != null) {
                        searchRemoteJobLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<RemoteJob>, t: Throwable) {
                    searchRemoteJobLiveData.postValue(null)
                    //pencarian dengan live data
                    Log.d("error ibm", t.message.toString())
                }

            }
        )

    }


    fun getRemoteJobResponseLiveData(): LiveData<RemoteJob> {
        return remoteJobResponseLiveData
    }

    fun getSearchJobResponseLiveData(): LiveData<RemoteJob> {
        return searchRemoteJobLiveData
    }


    suspend fun insertJob(job: JobToSave) = db.getRemoteJobDao().insertJob(job)
    suspend fun deleteJob(job: JobToSave) = db.getRemoteJobDao().deleteJob(job)
    fun getAllJobs() = db.getRemoteJobDao().getAllJob()

}
