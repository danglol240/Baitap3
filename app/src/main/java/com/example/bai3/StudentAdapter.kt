package com.example.bai3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

data class Student(
    val name: String,
    val studentId: String
)


class StudentAdapter(private val originalStudentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var filteredStudentList = originalStudentList.toMutableList()

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStudentName: TextView = itemView.findViewById(R.id.tvStudentName)
        val tvStudentID: TextView = itemView.findViewById(R.id.tvStudentID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = filteredStudentList[position]
        holder.tvStudentName.text = student.name
        holder.tvStudentID.text = student.studentId
    }

    override fun getItemCount(): Int {
        return filteredStudentList.size
    }

    // Hàm lọc danh sách dựa trên từ khóa tìm kiếm
    fun filter(query: String) {
        filteredStudentList = if (query.isEmpty()) {
            originalStudentList.toMutableList()
        } else {
            originalStudentList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.studentId.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
}
