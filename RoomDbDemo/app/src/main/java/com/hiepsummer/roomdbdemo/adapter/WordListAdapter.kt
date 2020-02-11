package com.hiepsummer.roomdbdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hiepsummer.roomdbdemo.R
import com.hiepsummer.roomdbdemo.model.Word

class WordListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<WordListAdapter.WorldViewHolder>() {

    private val inflate: LayoutInflater = LayoutInflater.from(context)
    private var worlds = emptyList<Word>()

    inner class WorldViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val worldItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorldViewHolder {
        val itemView = inflate.inflate(R.layout.recyclerview_item, parent, false)
        return WorldViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return worlds.size
    }

    override fun onBindViewHolder(holder: WorldViewHolder, position: Int) {
        val current = worlds[position]
        holder.worldItemView.text = current.word
    }

    internal fun setWorlds(worlds: List<Word>) {
        this.worlds = worlds
        notifyDataSetChanged()
    }

}