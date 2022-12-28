package com.goodaplikasi.nisaardiyanti.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.goodaplikasi.nisaardiyanti.models.JobToSave
//Todo 3: merupakan kelas room database, berfungsi dalam pengambilan database

@Dao
interface RemoteJobDao {
//interface RemoteJobDao
//no.8 Dao(query) terdapat pada baris 12-22
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     //Insert digunakan untuk menambahkan
    suspend fun insertJob(job: JobToSave): Long

    @Query("SELECT * FROM job ORDER BY id DESC")
        //Select digunakan untuk menampilkan dari tabel job dengan order by
    fun getAllJob(): LiveData<List<JobToSave>>

    @Delete
        //fungsi delete
    suspend fun deleteJob(job: JobToSave)
   // Fitur  Dao(query) terdapat pada class RemoteDao  baris 12-22
}
