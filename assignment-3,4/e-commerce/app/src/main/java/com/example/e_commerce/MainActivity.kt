package com.example.e_commerce

import UserData
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var createAccountButton: Button
    private lateinit var signInButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        UserData.users["user"] = "password"

        createAccountButton = findViewById<Button>(R.id.create_account_button)
        createAccountButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        signInButton = findViewById(R.id.sign_in_button)
        signInButton.setOnClickListener {
            val usernameEditText = findViewById<EditText>(R.id.username_edit_text)
            val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Please enter both username and password.")
            } else {
                if (UserData.users.containsKey(username)) {
                    if (UserData.users[username] == password) {
                        val intent = Intent(this@MainActivity, ShoppingActivity::class.java)
                        intent.putExtra("name", username)
                        startActivity(intent)
                    } else {
                        showToast("Incorrect password.")
                    }
                } else {
                    showToast("Username not found.")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}