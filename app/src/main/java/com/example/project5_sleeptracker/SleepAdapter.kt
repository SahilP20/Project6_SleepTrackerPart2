package com.example.project5_sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter : ListAdapter<SleepEntry, SleepAdapter.ViewHolder>(SleepDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sleep_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.sleep_date)
        private val durationTextView: TextView = itemView.findViewById(R.id.sleep_duration)
        private val qualityTextView: TextView = itemView.findViewById(R.id.sleep_quality)
        private val notesTextView: TextView = itemView.findViewById(R.id.sleep_notes)

        fun bind(item: SleepEntry) {
            dateTextView.text = item.date
            durationTextView.text = "${item.hoursSlept} hours"
            qualityTextView.text = "Quality: ${item.qualityRating}/10"
            notesTextView.text = item.notes ?: ""
        }
    }

    class SleepDiffCallback : DiffUtil.ItemCallback<SleepEntry>() {
        override fun areItemsTheSame(oldItem: SleepEntry, newItem: SleepEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SleepEntry, newItem: SleepEntry): Boolean {
            return oldItem == newItem
        }
    }
}
