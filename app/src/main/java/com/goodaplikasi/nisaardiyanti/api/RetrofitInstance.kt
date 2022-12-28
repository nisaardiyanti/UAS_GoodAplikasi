package com.goodaplikasi.nisaardiyanti.api


import com.goodaplikasi.nisaardiyanti.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//Todo 2: Melakukan parsing data hasil dari respon API
class RetrofitInstance {
//Kelas RetrofitInstance
    companion object {
//objek yang dideklarasikan dalam file yang sama dengan class ,
// dan memiliki nama yang sama dengan class. Objek pendamping dan kelasnya dapat mengakses anggota pribadi
// satu sama lain. Metode terapkan objek pengiring memungkinkan Anda membuat instance baru dari kelas tanpa
//  menggunakan kata kunci baru.
        private val retrofit by lazy {
            //validasi retrofit
            val logging = HttpLoggingInterceptor()
//no.5 network api (http request) terdapat pada baris 19-27
    //secara otomatis mencatat permintaan dan tanggapan HTTP yang masuk dan keluar ke Logcat,
    // di mana kita kemudian dapat melihat informasi seperti jenis permintaan,
    // URL yang diselesaikan sepenuhnya, jenis konten, header HTTP yang berbeda, dan muatan dari body itu sendiri
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
 //Mengembalikan daftar pabrik adaptor panggilan yang dapat dimodifikasi.
// Eksekutor tempat metode Callback dipanggil saat mengembalikan Call dari metode layanan Anda.
// Tentukan pabrik panggilan khusus untuk membuat instance Panggilan. Klien HTTP digunakan untuk permintaan.
                .baseUrl(BASE_URL)
    //Base URL  merupakan suatu dasar URL yang telah diatur atau dikonfigurasi

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: RemoteJobAPI by lazy {
        //validasi api dengan RemoteJobAPI
            retrofit.create(RemoteJobAPI::class.java)
   //membuat retrofit dengan  RemoteAPI menggunakan java class

        }

    }
}
