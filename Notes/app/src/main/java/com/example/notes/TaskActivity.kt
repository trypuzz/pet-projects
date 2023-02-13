package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityTaskBinding
    private val adapter = TaskRecyclerAdapter()
    private var editLauncher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.btnLogout.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
        init()
        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                adapter.addTask(it.data?.getSerializableExtra("task") as Task)
            }
        }
    }

    private fun init() {
        bindingClass.apply {
            rcTaskView.layoutManager = LinearLayoutManager(this@TaskActivity)
            rcTaskView.adapter = adapter
            btnAddNew.setOnClickListener {
                editLauncher?.launch(Intent(this@TaskActivity, AddNoteActivity::class.java))
            }
            TaskRepository.getTasks()
        }
    }
}