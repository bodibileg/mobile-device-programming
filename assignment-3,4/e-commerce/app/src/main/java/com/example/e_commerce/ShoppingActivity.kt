package com.example.e_commerce

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ShoppingActivity : AppCompatActivity() {
    private lateinit var welcomeTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val username = intent.getStringExtra("name")
        welcomeTextView = findViewById(R.id.welcome_text)
        welcomeTextView.text = "Welcome, $username!"
    }

    fun onCategoryClick(view: View) {
        val categoryName = view.tag.toString()
        showToast(categoryName)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}