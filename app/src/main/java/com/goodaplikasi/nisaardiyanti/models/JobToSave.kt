package com.goodaplikasi.nisaardiyanti.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job")
//9. entity(field) terdapat pada baris 7-22
data class JobToSave(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val candidateRequiredLocation: String?,
    val category: String?,
    val companyLogoUrl: String?,
    val companyName: String?,
    val description: String?,
    val jobId: Int?,
    val jobType: String?,
    val publicationDate: String?,
    val salary: String?,
    val title: String?,
    val url: String?
   // Fitur entity(field) terdapat pada  class JobToSave  baris 7-22
)
