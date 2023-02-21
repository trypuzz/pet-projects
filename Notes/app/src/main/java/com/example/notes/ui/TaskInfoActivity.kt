package com.example.notes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.notes.R
import com.example.notes.databinding.FragmentTaskInfoBinding


class TaskInfoActivity : AppCompatActivity() {

    lateinit var bindingClass: FragmentTaskInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = FragmentTaskInfoBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val taskName = intent.getStringExtra("TaskName")
        val messageTask = intent.getStringExtra("MessageTask")
        bindingClass.tvTaskInfo.text = taskName
        bindingClass.tvMessageTaskInfo.text = messageTask

        bindingClass.deleteTask.setOnClickListener {
            Log.d("MyLog", "delete")

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerDelete, DeleteTasksFragment())
                .commit()
        }

    }


    fun onClickBack(view: View){
        startActivity(Intent(this, TaskActivity::class.java))
    }

    fun onClickShareTask(view: View){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, bindingClass.tvMessageTaskInfo.text.toString())
        val chosenIntent = Intent.createChooser(intent, R.string.choosen_intent.toString())
        startActivity(chosenIntent)
    }

}
