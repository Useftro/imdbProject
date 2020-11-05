package com.example.imdbproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbproject.R
import com.example.imdbproject.model.CharacterModel
import kotlinx.android.synthetic.main.row_item.view.*
import java.util.ArrayList

class MainAdapter(private val characters: ArrayList<CharacterModel>): RecyclerView
                                                                .Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: CharacterModel) {
            itemView.apply {
                textViewCharacterName.text = character.name
                textViewCharacterStatus.text = character.status
                Glide.with(imageViewAvatar.context)
                    .load(character.image)
                    .into(imageViewAvatar)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false))

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    fun addCharacters(characters: List<CharacterModel>){
        this.characters.apply {
            clear()
            addAll(characters)
        }
    }

}