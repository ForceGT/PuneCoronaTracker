package com.gtxtreme.punecoronatracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import kotlinx.android.synthetic.main.ward_detail_rv_item.view.*

class WardAdapter(val data: List<WardData>):RecyclerView.Adapter<WardAdapter.WardDataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WardDataViewHolder {
        val myView =
            LayoutInflater.from(parent.context).inflate(R.layout.ward_detail_rv_item, parent, false)
        return WardDataViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: WardAdapter.WardDataViewHolder, position: Int) {
        holder.wardName.text = data[position].wardName
        holder.posCount.setCharacterLists(TickerUtils.provideNumberList())
        holder.posCount.animationDelay = 600
        holder.posCount.setText(data[position].positiveCount,true)
        holder.recoveredCount.setCharacterLists(TickerUtils.provideNumberList())
        holder.recoveredCount.animationDelay = 600
        holder.recoveredCount.setText(data[position].recoveredCount,true)
        holder.deathCount.setCharacterLists(TickerUtils.provideNumberList())
        holder.deathCount.animationDelay = 600
        holder.deathCount.setText(data[position].deathCount,true)
        holder.activeCount.setCharacterLists(TickerUtils.provideNumberList())
        holder.activeCount.animationDelay = 600
        holder.activeCount.setText(data[position].activeCount,true)
        setFadeAnimation(holder.itemView)
    }


    inner class WardDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wardName: TextView = itemView.wardNameTxt
        val posCount: TickerView = itemView.positiveCount
        val activeCount: TickerView = itemView.activeCount
        val recoveredCount : TickerView = itemView.recoveredCount
        val deathCount: TickerView = itemView.deathCount
    }

    private fun setFadeAnimation(itemView: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        itemView.startAnimation(anim)
    }

}