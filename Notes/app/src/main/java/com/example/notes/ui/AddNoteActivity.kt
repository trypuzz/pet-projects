package com.example.notes.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.notes.Invalid
import com.example.notes.dataBase.TaskRepository
//import com.example.firstapp.databinding.FragmentAddNoteBinding
import com.example.notes.databinding.FragmentAddTaskBinding
import com.example.notes.model.Task
import com.example.notes.validateMessageTasks
import com.example.notes.validateTitleTasks
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddNoteActivity : AppCompatActivity() {

    private lateinit var bindingClass: FragmentAddTaskBinding
    private val taskRepository = TaskRepository

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = FragmentAddTaskBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.btnBack.setOnClickListener {
        startActivity(Intent(this, TaskActivity::class.java))
    }
        onAddNewTask()
    }

    private fun validate(): Boolean {
        val isTitleTaskValid = validateTitleTask()
        val isMessageTaskValid = validateMessageTask()
        return isTitleTaskValid == null && isMessageTaskValid == null
    }

    private fun validateTitleTask(): String? {
        val titleTaskInputLayout = bindingClass.titlesContainer
        titleTaskInputLayout.editText?.let{
            val result = validateTitleTasks(it.text.toString())
            return when (result) {
                is Invalid -> {
                    titleTaskInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    titleTaskInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

    private fun validateMessageTask(): String? {
        val messageTaskInputLayout = bindingClass.messageContainer
        messageTaskInputLayout.editText?.let{
            val result = validateMessageTasks(it.text.toString())
            return when(result) {
                is Invalid -> {
                    messageTaskInputLayout.error = this.getString(result.textError)
                    this.getString(result.textError)
                }
                else -> {
                    messageTaskInputLayout.error = null
                    null
                }
            }
        } ?: return null
    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun onAddNewTask() = with(bindingClass){
        btnAdd.setOnClickListener {
            val nameTask = titlesEditText.text.toString()
            val messageTask = messageEditText.text?.toString()
            val dataTask = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
            val createTask = messageTask?.let { it1 -> Task(nameTask, it1, dataTask) }
            val editIntent = Intent().apply {
                putExtra("task", createTask)
            }
            setResult(RESULT_OK, editIntent)
            if (createTask != null) {
                TaskRepository.taskListSingleton.add(createTask)
            }
            if(validate())
            finish()
        }
    }
}



