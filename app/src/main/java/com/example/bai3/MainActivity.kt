package com.example.bai3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)

        // Tạo danh sách sinh viên mẫu
        val studentList = listOf(
            Student("Nguyen Nhat Huy", "5130"),
            Student("Nguyen Ngoc Le", "5225"),
            Student("Hoang Hai Dang", "5174"),
            Student("Nguyen Huy Vu Dung", "6574"),
            Student("Be Nguyen Ha Son", "5332")
        )

        // Cài đặt adapter và layout cho RecyclerView
        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Lắng nghe thay đổi văn bản trong EditText để lọc danh sách
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                studentAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
