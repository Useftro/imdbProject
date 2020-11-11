package com.example.imdbproject.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbproject.R
import com.example.imdbproject.activity.ChartScreen
import com.example.imdbproject.data.fromJsonToKotlin.Result

class FragmentListAdapter(private val mDataList: ArrayList<Result>) :
    RecyclerView.Adapter<FragmentListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).
                          inflate(R.layout.recyclerview_charactercard, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.characterNameTextView.text = mDataList[position].name
        holder.characterStatusTextView.text = mDataList[position].status
        holder.characterLocationTextView.text = mDataList[position].location.name
        Glide.with(holder.characterImageView.context).load(mDataList[position].image).into(holder.characterImageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ChartScreen::class.java)
            intent.putExtra("data", mDataList[position])
            holder.itemView.context.startActivity(intent)
             }

//        holder.itemView.setOnLongClickListener {
//
//        }

    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var characterNameTextView: TextView = itemView.findViewById<View>(R.id.nameTextView) as TextView
        internal var characterStatusTextView: TextView = itemView.findViewById<View>(R.id.characterStatusTextView) as TextView
        internal var characterLocationTextView: TextView = itemView.findViewById<View>(R.id.characterLocationTextView) as TextView
        internal var characterImageView: ImageView = itemView.findViewById<View>(R.id.characterImageView) as ImageView

    }
}