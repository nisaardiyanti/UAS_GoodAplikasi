package com.goodaplikasi.nisaardiyanti.models

import com.google.gson.annotations.SerializedName

data class RemoteJob(
//4.class data(objek data) pada baris 5-10
        @SerializedName("job-count")
        val jobCount: Int?,
        val jobs: List<Job>?,
        val legalNotice: String?
)

