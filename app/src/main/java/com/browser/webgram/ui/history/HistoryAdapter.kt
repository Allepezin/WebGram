package com.browser.webgram.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.browser.webgram.R
import com.browser.webgram.database.models.AppNote

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {
    private var listNotes = emptyList<AppNote>()

    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.findViewById(R.id.item_note_name)
        val textNote: TextView = view.findViewById(R.id.item_note_text)
    }

    override fun onViewAttachedToWindow(holder: HistoryHolder) {
        holder.itemView.setOnClickListener {
            HistoryFragment.click(listNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: HistoryHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.nameNote.text = listNotes[position].name
        holder.textNote.text = listNotes[position].text
    }

    override fun getItemCount(): Int = listNotes.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<AppNote>) {
        listNotes = list
        notifyDataSetChanged()
    }
}
