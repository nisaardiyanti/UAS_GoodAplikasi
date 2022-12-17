package com.goodaplikasi.nisaardiyanti.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goodaplikasi.nisaardiyanti.databinding.JobLayoutAdapterBinding
import com.goodaplikasi.nisaardiyanti.fragments.MainFragmentDirections
import com.goodaplikasi.nisaardiyanti.models.Job
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class RemoteJobAdapter : RecyclerView.Adapter<RemoteJobAdapter.RemoteJobViewHolder>() {
//Kelas RemoteJobAdapter
    private var binding: JobLayoutAdapterBinding? = null

    class RemoteJobViewHolder(itemBinding: JobLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
//ViewHolder menjelaskan tampilan item dan metadata tentang tempatnya dalam RecyclerView.
    private val differCallback = object :
        DiffUtil.ItemCallback<Job>() {
    //DiffUtil.Callback adalah class abstrak dan digunakan sebagai class callback
    //oleh DiffUtil saat menghitung selisih antara dua daftar.
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
//DiffUtil.Callback adalah class abstrak dan digunakan sebagai class callback
// oleh DiffUtil saat menghitung selisih antara dua daftar.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteJobViewHolder {
    //onCreateViewHolder hanya membuat penampung tampilan baru jika tidak ada penampung
    // tampilan yang dapat digunakan kembali oleh RecyclerView.
    // lalu menggunakannya kembali secara otomatis, setiap kali memanggil onBindViewHolder
        binding = JobLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RemoteJobViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RemoteJobViewHolder, position: Int) {
        //onBindViewHolder Dipanggil oleh RecyclerView untuk menampilkan data pada posisi yang ditentukan.
        // Metode ini harus memperbarui konten itemView untuk menampilkan item pada posisi tertentu.
        val currentJob = differ.currentList[position]

        holder.itemView.apply {

            Glide.with(this)
                .load(currentJob.companyLogoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding?.ivCompanyLogo!!)

            binding?.tvCompanyName?.text = currentJob.companyName
            //binding akan menghasilkan class binding berupa companyName
            binding?.tvJobLocation?.text = currentJob.candidateRequiredLocation
            //binding akan menghasilkan class binding berupa candidateRequiredLocation
            binding?.tvJobTitle?.text = currentJob.title
            //binding akan menghasilkan class binding berupa tittle
            binding?.tvJobType?.text = currentJob.jobType
            //binding akan menghasilkan class binding berupa jobType

            val dateJob = currentJob.publicationDate?.split("T")
            binding?.tvDate?.text = dateJob?.get(0)
            //binding akan menghasilkan class binding berupa tanggal job

        }.setOnClickListener { mView ->
            //OnClickListener() memiliki metode onClick(View v) yang dipanggil saat tampilan (komponen) diklik
            val direction = MainFragmentDirections
                .actionMainFragmentToJobDetailsFragment(currentJob)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}