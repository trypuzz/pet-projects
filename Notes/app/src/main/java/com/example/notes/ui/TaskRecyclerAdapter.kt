package com.example.notes.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.Task
import com.example.notes.ui.adapter.TaskViewHolder


class TaskRecyclerAdapter(
    private val onClick:(task: Task) -> Unit
    ): RecyclerView.Adapter<TaskViewHolder>() {

    private var taskList = ArrayList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
        holder.itemView.setOnClickListener {
            onClick(taskList[position])
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTask(task: Task){
        taskList.add(task)
        notifyDataSetChanged()
    }
}