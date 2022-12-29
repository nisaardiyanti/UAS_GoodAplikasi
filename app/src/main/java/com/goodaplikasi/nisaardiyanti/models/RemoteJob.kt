package com.goodaplikasi.nisaardiyanti.models

import com.google.gson.annotations.SerializedName
//todo 5: kelas model yang berfungsi untuk merepresentasikan data yang akan diproses dan menentukan properti dan atribut kelas model
data class RemoteJob(
//4.class data(objek data) pada baris 5-10
        @SerializedName("job-count")
        val jobCount: Int?,
        val jobs: List<Job>?,
        val legalNotice: String?
)

