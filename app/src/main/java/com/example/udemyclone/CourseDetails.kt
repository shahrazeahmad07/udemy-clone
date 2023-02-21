package com.example.udemyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.udemyclone.databinding.ActivityCourseDetailsBinding
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class CourseDetails : AppCompatActivity() {
    lateinit var binding: ActivityCourseDetailsBinding

    private lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var course: CourseRVModal

    lateinit var courseID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        courseID = intent.getStringExtra("CourseID").toString()

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("Courses").child(courseID)

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

        binding.btnEditCourse.setOnClickListener {
            val intent = Intent(this, EditCourseActivity::class.java)
            intent.putExtra("CourseID", courseID)
            startActivity(intent)
            finish()
        }

        //! remaining
        binding.btnDeleteCourse.setOnClickListener {
            databaseReference.removeValue()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}