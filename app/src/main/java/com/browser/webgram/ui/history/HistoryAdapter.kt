package com.browser.webgram.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.browser.webgram.R
import com.browser.webgram.database.models.AppNote

class HistoryAdapter(inline val onClick: (AppNote) -> Unit) :
    ListAdapter<AppNote, HistoryAdapter.HistoryHolder>(HistoryDiffUtilCallback()) {

    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.findViewById(R.id.item_note_name)
        val textNote: TextView = view.findViewById(R.id.item_note_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.nameNote.text = currentList[position].name
        holder.textNote.text = currentList[position].text

        holder.itemView.setOnClickListener {
            onClick(currentList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = currentList.size
}
