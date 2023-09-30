package com.browser.webgram.ui.history

import androidx.recyclerview.widget.DiffUtil
import com.browser.webgram.database.models.AppNote

class HistoryDiffUtilCallback : DiffUtil.ItemCallback<AppNote>()  {

    override fun areItemsTheSame(oldItem: AppNote, newItem: AppNote): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AppNote,
        newItem: AppNote
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: AppNote, newItem: AppNote): Any? {
        return super.getChangePayload(oldItem, newItem)
    }
}