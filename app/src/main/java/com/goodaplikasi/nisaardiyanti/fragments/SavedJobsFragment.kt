package com.goodaplikasi.nisaardiyanti.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodaplikasi.nisaardiyanti.MainActivity
import com.goodaplikasi.nisaardiyanti.R
import com.goodaplikasi.nisaardiyanti.adapters.RemoteJobSavedAdapter
import com.goodaplikasi.nisaardiyanti.databinding.FragmentSavedJobsBinding
import com.goodaplikasi.nisaardiyanti.models.JobToSave
import com.goodaplikasi.nisaardiyanti.viewmodel.RemoteJobViewModel


class SavedJobsFragment : Fragment(R.layout.fragment_saved_jobs),
    RemoteJobSavedAdapter.OnItemClickListener {

    private var _binding: FragmentSavedJobsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var jobAdapter: RemoteJobSavedAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedJobsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        jobAdapter = RemoteJobSavedAdapter(this)

        binding.rvJobsSaved.apply {

            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(
                object : DividerItemDecoration(
                    activity, LinearLayout.VERTICAL) {})
            adapter = jobAdapter
        }

        viewModel.getAllJob().observe(viewLifecycleOwner, { jobToSave ->
            jobAdapter.differ.submitList(jobToSave)
            updateUI(jobToSave)
        })
    }

    private fun updateUI(list: List<JobToSave>) {

        if (list.isNotEmpty()) {
            binding.rvJobsSaved.visibility = View.VISIBLE
            binding.cardNoAvailable.visibility = View.GONE
        } else {
            binding.rvJobsSaved.visibility = View.GONE
            binding.cardNoAvailable.visibility = View.VISIBLE
        }
    }


    override fun onItemClick(job: JobToSave, view: View, position: Int) {
       deleteJob(job)
    }


    private fun deleteJob(job: JobToSave) {

        AlertDialog.Builder(activity).apply {
            setTitle("Hapus Loker")
            setMessage("Apakah anda ingin menghapus loker?")
            setPositiveButton("Hapus") { _, _ ->
                viewModel.deleteJob(job)
               Toast.makeText(activity,"Loker Berhasil di Hapus", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Batal", null)
        }.create().show()

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}