package com.example.quicknotes

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNote: EditText
    private lateinit var btnSave: Button
    private lateinit var btnRetrieve: Button
    private lateinit var btnClear: Button
    private lateinit var tvResult: TextView

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "QuickNotesPrefs"
    private val KEY_NOTE = "note"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        etNote = findViewById(R.id.etNote)
        btnSave = findViewById(R.id.btnSave)
        btnRetrieve = findViewById(R.id.btnRetrieve)
        btnClear = findViewById(R.id.btnClear)
        tvResult = findViewById(R.id.tvResult)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        // Save note
        btnSave.setOnClickListener {
            val note = etNote.text.toString()
            if (note.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString(KEY_NOTE, note)
                editor.apply()
                tvResult.text = "Note saved successfully!"
            } else {
                tvResult.text = "Please enter a note to save."
            }
        }

        // Retrieve saved note
        btnRetrieve.setOnClickListener {
            val savedNote = sharedPreferences.getString(KEY_NOTE, "No note saved.")
            tvResult.text = "Saved Note:\n$savedNote"
        }

        // Clear saved note
        btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            tvResult.text = "Note cleared!"
        }
    }
}
