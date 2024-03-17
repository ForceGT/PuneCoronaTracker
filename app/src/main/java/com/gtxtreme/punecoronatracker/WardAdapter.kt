package com.gtxtreme.punecoronatracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gtxtreme.punecoronatracker.databinding.WardDetailRvItemBinding
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView

class WardAdapter(val data: List<WardData>) :
    RecyclerView.Adapter<WardAdapter.WardDataViewHolder>() {


    private lateinit var binding: WardDetailRvItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WardDataViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.ward_detail_rv_item,
            parent,
            false
        )
        return WardDataViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: WardAdapter.WardDataViewHolder, position: Int) {
        holder.apply {
            wardName.text = data[position].wardName
            posCount.setCharacterLists(TickerUtils.provideNumberList())
            posCount.animationDelay = 600
            posCount.setText(data[position].positiveCount, true)
            recoveredCount.setCharacterLists(TickerUtils.provideNumberList())
            recoveredCount.animationDelay = 600
            recoveredCount.setText(data[position].recoveredCount, true)
            deathCount.setCharacterLists(TickerUtils.provideNumberList())
            deathCount.animationDelay = 600
            deathCount.setText(data[position].deathCount, true)
            activeCount.setCharacterLists(TickerUtils.provideNumberList())
            activeCount.animationDelay = 600
            activeCount.setText(data[position].activeCount, true)
            setFadeAnimation(itemView)
        }

    }


    inner class WardDataViewHolder(binding: WardDetailRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val wardName: TextView = binding.wardNameTxt
        val posCount: TickerView = binding.positiveCount
        val activeCount: TickerView = binding.activeCount
        val recoveredCount: TickerView = binding.recoveredCount
        val deathCount: TickerView = binding.deathCount
    }

    private fun setFadeAnimation(itemView: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        itemView.startAnimation(anim)
    }

}