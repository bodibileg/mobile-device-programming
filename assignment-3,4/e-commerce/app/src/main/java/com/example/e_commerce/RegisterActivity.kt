package com.example.e_commerce

import UserData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var mobileOrEmailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var reenterPasswordEditText: EditText
    private lateinit var continueButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.editText_name)
        mobileOrEmailEditText = findViewById(R.id.editText_email)
        passwordEditText = findViewById(R.id.editText_password)
        reenterPasswordEditText = findViewById(R.id.editText_confirm)
        continueButton = findViewById(R.id.button_continue)
        continueButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val mobileOrEmail = mobileOrEmailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val reenterPassword = reenterPasswordEditText.text.toString().trim()

            if (name.isEmpty() || mobileOrEmail.isEmpty() || password.isEmpty() || reenterPassword.isEmpty()) {
                showToast("Please fill all fields.")
            } else if (password != reenterPassword) {
                showToast("Password and re-entered password do not match.")
            } else {
                if (userAlreadyExists(name)) {
                    showToast("Username already exists.")
                } else {
                    UserData.users[name] = password
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun userAlreadyExists(username: String): Boolean {
        return UserData.users.containsKey(username)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}