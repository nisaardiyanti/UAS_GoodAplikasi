package com.goodaplikasi.nisaardiyanti.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.goodaplikasi.nisaardiyanti.models.JobToSave

@Database(entities = [JobToSave::class], version = 1)
//no.9 entity(field) dari baris 9-28
abstract class RemoteJobDatabase : RoomDatabase() {
//kelas abstrak RemoteJobDatabase
    abstract fun getRemoteJobDao(): RemoteJobDao
//function untuk mengambil RemoteJobJobDao
    companion object {
//objek yang dideklarasikan dalam file yang sama dengan class , dan memiliki nama yang sama dengan class.
    // Objek pendamping dan kelasnya dapat mengakses anggota pribadi satu sama lain.
    // Metode terapkan objek pengiring memungkinkan Anda membuat instance baru dari kelas tanpa
    // menggunakan kata kunci baru.    
        @Volatile
        private var instance: RemoteJobDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
 //private function membuat database remoteJob_db2
            Room.databaseBuilder(
//no.7 RoomDb(Konfigurasi db) terdapat pada baris 30-35            
                context.applicationContext,
                RemoteJobDatabase::class.java,
                "remoteJob_db2"
            ).build()
//Fitur RoomDb(Konfigurasi db) Tedapat pada Class RemoteJobDatabase baris 30-35
    }
}
