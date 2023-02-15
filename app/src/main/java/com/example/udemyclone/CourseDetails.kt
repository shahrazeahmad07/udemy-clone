package com.example.udemyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.udemyclone.databinding.ActivityCourseDetailsBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class CourseDetails : AppCompatActivity() {
    lateinit var binding: ActivityCourseDetailsBinding

    private lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var course: CourseRVModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("Courses").child(intent.getStringExtra("Course Name").toString())

        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(CourseRVModal::class.java)?.let {
                    course = it
                    Picasso.get().load(course.courseImageLink.toString()).into(binding.ivCourseImage)

                    binding.tvCourseTitle.text = course.courseName.toString()
                    binding.tvCourseDescription.text = course.courseDescription.toString()
                    val price = "Price: \$${course.coursePrice.toString()}"
                    binding.tvPrice.text = price
                    val suitedFor = "Suited For: ${course.courseSuitedFor.toString()}"
                    binding.tvSuitedFor.text = suitedFor
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(baseContext, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}