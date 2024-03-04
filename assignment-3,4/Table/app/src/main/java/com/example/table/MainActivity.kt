package com.example.table

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var waterResourceNameEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var typeEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        waterResourceNameEditText = findViewById(R.id.editTextWaterResourceName)
        locationEditText = findViewById(R.id.editTextLocation)
        typeEditText = findViewById(R.id.editTextType)
        submitButton = findViewById(R.id.buttonSubmit)
        tableLayout = findViewById(R.id.tableLayout)

        submitButton.setOnClickListener {
            val waterResourceName = waterResourceNameEditText.text.toString().trim()
            val location = locationEditText.text.toString().trim()
            val type = typeEditText.text.toString().trim()

            if (waterResourceName.isEmpty() || location.isEmpty() || type.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                addRowToTable(waterResourceName, location, type)
                clearEditTexts()
            }
        }
    }

    private fun addRowToTable(waterResourceName: String, location: String, type: String) {
        val newRow = TableRow(this)
        newRow.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )

        val layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        val waterResourceNameTextView = TextView(this)
        waterResourceNameTextView.text = waterResourceName
        waterResourceNameTextView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        waterResourceNameTextView.gravity = Gravity.CENTER
        waterResourceNameTextView.setPadding(3, 3, 3, 3)
        layoutParams.setMargins(1, 1, 1, 1)
        waterResourceNameTextView.layoutParams = layoutParams

        val locationTextView = TextView(this)
        locationTextView.text = location
        locationTextView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        locationTextView.gravity = Gravity.CENTER
        locationTextView.setPadding(3, 3, 3, 3)
        locationTextView.layoutParams = layoutParams

        val typeTextView = TextView(this)
        typeTextView.text = type
        typeTextView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        typeTextView.gravity = Gravity.CENTER
        typeTextView.setPadding(3, 3, 3, 3)
        typeTextView.layoutParams = layoutParams

        newRow.addView(waterResourceNameTextView)
        newRow.addView(locationTextView)
        newRow.addView(typeTextView)

        tableLayout.addView(newRow)
    }

    private fun clearEditTexts() {
        waterResourceNameEditText.text.clear()
        locationEditText.text.clear()
        typeEditText.text.clear()
    }
}