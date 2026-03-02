package com.example.project5_sleeptracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SleepAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.sleep_list)
        adapter = SleepAdapter()
        recyclerView.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.add_sleep_button)
        fab.setOnClickListener {
            val intent = Intent(this, CreateEntryActivity::class.java)
            startActivity(intent)
        }

        val database = SleepDatabase.getInstance(this)
        
        lifecycleScope.launch {
            database.sleepDao.getAllSleepEntries().collect { entries ->
                adapter.submitList(entries)
            }
        }
    }
}
