package com.example.udemyclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CourseRVAdapter(private val courseRVModalArrayList: ArrayList<CourseRVModal>, val context: Context, val courseClickInterface: CourseClickInterface) : RecyclerView.Adapter<CourseRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_course_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCourseTitle.text = courseRVModalArrayList[position].courseName
        holder.tvCoursePrice.text = courseRVModalArrayList[position].coursePrice.toString()
        Picasso.get().load(courseRVModalArrayList[position].courseImageLink).into(holder.ivCourseImage)
        holder.itemView.setOnClickListener {
            courseClickInterface.onCourseClick(position)
        }
    }

    override fun getItemCount(): Int {
        return courseRVModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivCourseImage: ImageView
        var tvCourseTitle: TextView
        var tvCoursePrice: TextView

        init {
            ivCourseImage = itemView.findViewById(R.id.ivCourseImage)
            tvCourseTitle = itemView.findViewById(R.id.tvCourseTitle)
            tvCoursePrice = itemView.findViewById(R.id.tvCoursePrice)
        }
    }
    interface CourseClickInterface {
        fun onCourseClick(position: Int)
    }
}