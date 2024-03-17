package com.gtxtreme.punecoronatracker

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gtxtreme.punecoronatracker.databinding.RvHomeItemBinding
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView


class HomeAdapter(private val userList: ArrayList<HomeData>) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private lateinit var binding: RvHomeItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_home_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = userList[position].title
        holder.subtitle.animationDuration = 3000
        holder.subtitle.animationInterpolator = OvershootInterpolator()
        holder.subtitle.gravity = Gravity.CENTER_HORIZONTAL
        holder.subtitle.setPreferredScrollingDirection(TickerView.ScrollingDirection.DOWN)
        holder.subtitle.setCharacterLists(TickerUtils.provideNumberList());
        holder.subtitle.setText(userList[position].count, true)
        holder.subtitle.typeface =
            ResourcesCompat.getFont(holder.subtitle.context, R.font.poppins_light)
        setFadeAnimation(holder.itemView)
    }

    private fun setFadeAnimation(itemView: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        itemView.startAnimation(anim)
    }

    class MyViewHolder(binding: RvHomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.tvtitle
        val subtitle: TickerView = binding.tvsubtitle

    }

}