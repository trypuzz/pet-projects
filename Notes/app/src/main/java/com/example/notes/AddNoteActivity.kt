package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var bindingClass: ActivityAddNoteBinding
    private val taskRepository = TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.btnBack.setOnClickListener {
        startActivity(Intent(this, TaskActivity::class.java))
    }
        initButtons()
    }

    private fun initButtons() = with(bindingClass){
        btnAdd.setOnClickListener {
            val createTask = Task(titlesEditText.text.toString(), messageEditText.text.toString(), editTextDate.text.toString())
            val editIntent = Intent().apply {
                putExtra("task", createTask)
            }
            setResult(RESULT_OK, editIntent)
            taskRepository.addNewTask(createTask)
            finish()
        }
    }
}



