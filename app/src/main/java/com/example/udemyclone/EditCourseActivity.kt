package com.example.udemyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.udemyclone.databinding.ActivityEditCourseBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditCourseBinding

    private lateinit var courseID: String

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var course: CourseRVModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        courseID = intent.getStringExtra("CourseID").toString()

        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.getReference("Courses").child(courseID)



        binding.btnCancel.setOnClickListener {
            val intent1 = Intent(this, CourseDetails::class.java)
            intent1.putExtra("CourseID", courseID)
            startActivity(intent1)
            finish()
        }

        fetchCourseDetails()

        binding.btnUpdateCourse.setOnClickListener {
            val courseName = binding.etCourseName.text.toString()
            val coursePrice = binding.etCoursePrice.text.toString()
            val courseSuitedFor = binding.etCourseSuitedFor.text.toString()
            val courseImageLink = binding.etCourseImageLink.text.toString()
            val courseLink = binding.etCourseLink.text.toString()
            val courseDescription = binding.etCourseDescription.text.toString()

            if (courseName.isEmpty() || coursePrice.isEmpty() || courseSuitedFor.isEmpty() || courseImageLink.isEmpty() || courseLink.isEmpty() || courseDescription.isEmpty()) {
                Toast.makeText(this, "Enter all fields first!!", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBar.visibility = View.VISIBLE
                databaseReference.removeValue()

                val course = CourseRVModal(
                    courseName,
                    coursePrice.toDouble(),
                    courseSuitedFor,
                    courseImageLink,
                    courseLink,
                    courseDescription
                )
                databaseReference = firebaseDatabase.getReference("Courses").child(courseName)
                databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        databaseReference.setValue(course)
                        val intent = Intent(this@EditCourseActivity, CourseDetails::class.java)
                        intent.putExtra("CourseID", course.courseName)
                        startActivity(intent)
                        finish()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            this@EditCourseActivity,
                            error.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }
        }
    }

    private fun fetchCourseDetails() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(CourseRVModal::class.java)?.let {
                    course = it
                    val courseName = course.courseName
                    val coursePrice = course.coursePrice.toString()
                    val courseSuitedFor = course.courseSuitedFor
                    val courseImageLink = course.courseImageLink
                    val courseLink = course.courseLink
                    val courseDescription = course.courseDescription

                    binding.etCourseName.setText(courseName)
                    binding.etCoursePrice.setText(coursePrice)
                    binding.etCourseSuitedFor.setText(courseSuitedFor)
                    binding.etCourseImageLink.setText(courseImageLink)
                    binding.etCourseLink.setText(courseLink)
                    binding.etCourseDescription.setText(courseDescription)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EditCourseActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent1 = Intent(this, CourseDetails::class.java)
        intent1.putExtra("CourseID", courseID)
        startActivity(intent1)
        finish()
    }
}