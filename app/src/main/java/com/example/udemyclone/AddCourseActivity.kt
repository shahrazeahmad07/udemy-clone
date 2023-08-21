package com.example.udemyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.udemyclone.databinding.ActivityAddCourseBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCourseBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Add Course"

        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.getReference("Courses")
        
        binding.btnAddCourse.setOnClickListener {
            val courseName = binding.etCourseName.text.toString()
            val coursePrice = binding.etCoursePrice.text.toString()
            val courseSuitedFor = binding.etCourseSuitedFor.text.toString()
            val courseImageLink = binding.etCourseImageLink.text.toString()
            val courseLink = binding.etCourseLink.text.toString()
            val courseDescription = binding.etCourseDescription.text.toString()
            
            if (courseName.isEmpty() || coursePrice.isEmpty() || courseSuitedFor.isEmpty() || courseImageLink.isEmpty() || courseLink.isEmpty() || courseDescription.isEmpty()) {
                Toast.makeText(this, "Enter all fields first!!", Toast.LENGTH_SHORT).show()
            }
            else {
                val courseRVModal = CourseRVModal(courseName, coursePrice.toDouble(), courseSuitedFor, courseImageLink, courseLink, courseDescription)

                databaseReference.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        databaseReference.child(courseName).setValue(courseRVModal)
                        Toast.makeText(this@AddCourseActivity, "Course Added!!!", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            this@AddCourseActivity,
                            "Error: $error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }
}