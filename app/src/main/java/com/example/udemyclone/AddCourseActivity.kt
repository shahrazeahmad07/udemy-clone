package com.example.udemyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.udemyclone.databinding.ActivityAddCourseBinding

class AddCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Add Course"
    }
}