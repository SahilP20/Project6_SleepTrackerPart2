package com.example.project5_sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val avgHoursValue = view.findViewById<TextView>(R.id.avg_hours_value)
        val avgQualityValue = view.findViewById<TextView>(R.id.avg_quality_value)

        val database = SleepDatabase.getInstance(requireContext())

        lifecycleScope.launch {
            database.sleepDao.getAllSleepEntries().collect { entries ->
                if (entries.isNotEmpty()) {
                    val avgHours = entries.map { it.hoursSlept }.average()
                    val avgQuality = entries.map { it.qualityRating }.average()

                    avgHoursValue.text = String.format("%.1f hours", avgHours)
                    avgQualityValue.text = String.format("%.1f / 10", avgQuality)
                } else {
                    avgHoursValue.text = "0.0 hours"
                    avgQualityValue.text = "0.0 / 10"
                }
            }
        }

        return view
    }
}
