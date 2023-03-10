package com.goodaplikasi.nisaardiyanti.api


import com.goodaplikasi.nisaardiyanti.models.RemoteJob
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//Todo 1 : bagian retrofit yang akan mengambil data dari API
//bagian retrofit yang akan mengambil data repository pada class RemoteJobAPI.kt pada baris ke 10-20
interface RemoteJobAPI {
    //no.5 network api (parsing data ) terdapat pada baris 14-19
    //Mengirim permintaan ke API dan interface sebagai tampilan atau antarmuka yang tujuannya,agar pengguna dapat berinteraksi
// atau menggunakan perangkatnya digital secara langsung maupun melalui jaringan   
    @GET("remote-jobs")
//menggunakan fungsi get
    fun getRemoteJob(): Call<RemoteJob>
//function untuk memanggil
    @GET("remote-jobs")
    fun searchRemoteJob(@Query("search") query: String?): Call<RemoteJob>

}
