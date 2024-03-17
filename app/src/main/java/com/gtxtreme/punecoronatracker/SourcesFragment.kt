package com.gtxtreme.punecoronatracker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import com.gtxtreme.punecoronatracker.databinding.FragmentSourcesBinding


class SourcesFragment : Fragment() {

    private var link: String? = null

    // Views
    private lateinit var binding: FragmentSourcesBinding
    private lateinit var imgFb: ImageButton
    private lateinit var imgInsta: ImageButton
    private lateinit var imgGit: ImageButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sources,
            container,
            false
        )
        imgFb = binding.imgFb
        imgGit = binding.imgGit
        imgInsta = binding.imgInsta
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgFb.setOnClickListener {
            link = "https://www.facebook.com/gaurav.thakkar.5682/"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
        imgGit.setOnClickListener {
            link = "https://www.github.com/ForceGT"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
        imgInsta.setOnClickListener {
            link = "https://www.instagram.com/gt_xtreme"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        }
    }
}
