package com.example.notes.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.Task


class TaskRecyclerAdapter(
    private val onClick: (task: Task) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {

    private var taskList = arrayListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        )
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
    fun addNewTask(taskList: ArrayList<Task>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }

}