package com.example.project5_sleeptracker

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class CreateEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        val dateInput = findViewById<TextInputEditText>(R.id.date_input)
        val hoursInput = findViewById<TextInputEditText>(R.id.hours_input)
        val qualityInput = findViewById<TextInputEditText>(R.id.quality_input)
        val notesInput = findViewById<TextInputEditText>(R.id.notes_input)
        val saveButton = findViewById<Button>(R.id.save_button)

        val database = SleepDatabase.getInstance(this)

        saveButton.setOnClickListener {
            val date = dateInput.text.toString()
            val hours = hoursInput.text.toString().toDoubleOrNull() ?: 0.0
            val quality = qualityInput.text.toString().toIntOrNull() ?: 0
            val notes = notesInput.text.toString()

            if (date.isNotEmpty()) {
                val newEntry = SleepEntry(
                    date = date,
                    hoursSlept = hours,
                    qualityRating = quality,
                    notes = notes
                )

                lifecycleScope.launch {
                    database.sleepDao.insert(newEntry)
                    finish()
                }
            }
        }
    }
}
