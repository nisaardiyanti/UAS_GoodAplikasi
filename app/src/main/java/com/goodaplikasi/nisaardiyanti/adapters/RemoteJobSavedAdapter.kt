package com.goodaplikasi.nisaardiyanti.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goodaplikasi.nisaardiyanti.databinding.JobLayoutAdapterBinding
import com.goodaplikasi.nisaardiyanti.fragments.MainFragmentDirections
import com.goodaplikasi.nisaardiyanti.models.Job
import com.goodaplikasi.nisaardiyanti.models.JobToSave
import com.bumptech.glide.Glide

class RemoteJobSavedAdapter constructor(
    //kelas RemoteJobSaverAdapter
    private val itemClick: OnItemClickListener)
    : RecyclerView.Adapter<RemoteJobSavedAdapter.RemoteJobViewHolder>() {

    private var binding: JobLayoutAdapterBinding? = null

    class RemoteJobViewHolder(itemBinding: JobLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
//ViewHolder menjelaskan tampilan item dan metadata tentang tempatnya dalam RecyclerView.
    private val differCallback = object :
        DiffUtil.ItemCallback<JobToSave>() {
    //DiffUtil.Callback adalah class abstrak dan digunakan sebagai class callback
    //oleh DiffUtil saat menghitung selisih antara dua daftar.
        override fun areItemsTheSame(oldItem: JobToSave, newItem: JobToSave): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobToSave, newItem: JobToSave): Boolean {
            return newItem == oldItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
//DiffUtil.Callback adalah class abstrak dan digunakan sebagai class callback
// oleh DiffUtil saat menghitung selisih antara dua daftar.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteJobViewHolder {
        binding = JobLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RemoteJobViewHolder(binding!!)

    }

    override fun onBindViewHolder(holder: RemoteJobViewHolder, position: Int) {
        val currentJob = differ.currentList[position]

        holder.itemView.apply {

            Glide.with(this)
                .load(currentJob.companyLogoUrl)
                .into(binding?.ivCompanyLogo!!)

            binding?.tvCompanyName?.text = currentJob.companyName
            //binding akan menghasilkan class binding berupa companyName
            binding?.tvJobLocation?.text = currentJob.candidateRequiredLocation
            //binding akan menghasilkan class binding berupa candidateRequiredLocation
            binding?.tvJobTitle?.text = currentJob.title
            //binding akan menghasilkan class binding berupa  title
            binding?.tvJobType?.text = currentJob.jobType
            //binding akan menghasilkan class binding berupa jobType
            binding?.ibDelete?.visibility = View.VISIBLE

            val dateJob = currentJob.publicationDate?.split("T")
            binding?.tvDate?.text = dateJob?.get(0)
//binding akan menghasilkan class binding berupa tanggal job
        }.setOnClickListener { mView ->
            //OnClickListener() memiliki metode onClick(View v) yang dipanggil saat tampilan (komponen) diklik
            val tags = arrayListOf<String>()
            val job = Job(
                currentJob.candidateRequiredLocation, currentJob.category,
                currentJob.companyLogoUrl, currentJob.companyName,
                currentJob.description, currentJob.id, currentJob.jobType,
                currentJob.publicationDate, currentJob.salary, tags,
                currentJob.title, currentJob.url
            )

            val direction = MainFragmentDirections
                .actionMainFragmentToJobDetailsFragment(job)
            mView.findNavController().navigate(direction)
        }


        holder.itemView.apply{
            binding?.ibDelete?.setOnClickListener {
               //binding akan menghasilkan class binding berupa ibdelete
                itemClick.onItemClick(
                    //OnItemClick() memiliki metode onClick(View v) yang dipanggil saat tampilan (komponen) item  diklik
                    currentJob,
                    binding?.ibDelete!!,
                    //binding akan menghasilkan class binding berupa ibdelete
                    position
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnItemClickListener {
        fun onItemClick(
            job: JobToSave,
            view: View,
            position: Int
        )
    }
}