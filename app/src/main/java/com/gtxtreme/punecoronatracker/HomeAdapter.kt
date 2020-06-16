package com.gtxtreme.punecoronatracker

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import kotlinx.android.synthetic.main.rv_home_item.view.*


class HomeAdapter (private val userList: ArrayList<HomeData>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myView = LayoutInflater.from(parent.context).inflate(R.layout.rv_home_item,parent,false)
        return MyViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = userList[position].title
        holder.subtitle.animationDuration = 3000
        holder.subtitle.animationInterpolator = OvershootInterpolator()
        holder.subtitle.gravity = Gravity.CENTER_HORIZONTAL
        holder.subtitle.setPreferredScrollingDirection(TickerView.ScrollingDirection.DOWN)
        holder.subtitle.setCharacterLists(TickerUtils.provideNumberList());
        holder.subtitle.setText(userList[position].count,true)
        holder.subtitle.typeface= ResourcesCompat.getFont(holder.subtitle.context,R.font.poppins_light)
        setFadeAnimation(holder.itemView)
    }

    private fun setFadeAnimation(itemView: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        itemView.startAnimation(anim)
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.tvtitle
        val subtitle: TickerView = itemView.tvsubtitle

    }

}