package com.example.udemyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.udemyclone.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //! Already a user Functionality
        binding.tvLoginHere.setOnClickListener {
            finish()
        }

        //! Register Button
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (username.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
                Toast.makeText(this, "Required fields empty..", Toast.LENGTH_SHORT).show()
            }
            else if (password != confirmPassword) {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()
            }
            else {
                binding.progressBar.visibility = View.VISIBLE
                val mAuth = FirebaseAuth.getInstance()
                mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener {
                    if(it.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                        Toast.makeText(this,"User Registered!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Fail to Register User..", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}