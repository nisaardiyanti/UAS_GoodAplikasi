package com.goodaplikasi.nisaardiyanti.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.goodaplikasi.nisaardiyanti.MainActivity
import com.goodaplikasi.nisaardiyanti.R
import com.goodaplikasi.nisaardiyanti.databinding.FragmentJobDetailsBinding
import com.goodaplikasi.nisaardiyanti.models.Job
import com.goodaplikasi.nisaardiyanti.models.JobToSave
import com.goodaplikasi.nisaardiyanti.viewmodel.RemoteJobViewModel
import com.google.android.material.snackbar.Snackbar


class JobDetailsFragment : Fragment(R.layout.fragment_job_details) {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: JobDetailsFragmentArgs by navArgs()
    private lateinit var currentJob: Job
    private lateinit var viewModel: RemoteJobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJobDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        currentJob = args.job!!

        setUpWebView()

        binding.fabAddFavorite.setOnClickListener {
            addJobToFavorite(view)
        }

    }

    private fun setUpWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob.url?.let { loadUrl(it) }
        }

        binding.webView.settings.apply {
            javaScriptEnabled = true
            setAppCacheEnabled(true)
            cacheMode = WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls = false
            textZoom = 100
            blockNetworkImage = false
            loadsImagesAutomatically = true
        }
    }

    private fun addJobToFavorite(view: View) {
        val job = JobToSave(
            0,
            currentJob.candidateRequiredLocation, currentJob.category,
            currentJob.companyLogoUrl, currentJob.companyName,
            currentJob.description, currentJob.id, currentJob.jobType,
            currentJob.publicationDate, currentJob.salary, currentJob.title, currentJob.url
        )

        viewModel.insertJob(job)
        Snackbar.make(view, "Halaman Berhasil Di simpan", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}