package com.gtxtreme.punecoronatracker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sources.*


class SourcesFragment : Fragment() {

    var link:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_sources, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgFb.setOnClickListener{
            link="https://www.facebook.com/gaurav.thakkar.5682/"
            startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(link)))
        }
        imgGit.setOnClickListener {
            link="https://www.github.com/ForceGT"
            startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(link)))
        }
        imgInsta.setOnClickListener{
            link="https://www.instagram.com/gt_xtreme"
            startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(link)))
        }
    }



}
