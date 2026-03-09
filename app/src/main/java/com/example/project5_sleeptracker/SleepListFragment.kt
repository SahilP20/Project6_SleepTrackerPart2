package com.example.project5_sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class SleepListFragment : Fragment() {

    private lateinit var adapter: SleepAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sleep_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.sleep_list)
        adapter = SleepAdapter()
        recyclerView.adapter = adapter

        val database = SleepDatabase.getInstance(requireContext())
        
        lifecycleScope.launch {
            database.sleepDao.getAllSleepEntries().collect { entries ->
                adapter.submitList(entries)
            }
        }

        return view
    }
}
