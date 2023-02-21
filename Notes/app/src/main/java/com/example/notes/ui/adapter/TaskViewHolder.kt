package com.example.notes.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.TaskItemBinding
import com.example.notes.model.Task

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = TaskItemBinding.bind(view)

    fun bind(task: Task) = with(binding) {
        tvNameTask.text = task.nameTask
        tvMessageTask.text = task.messageTask
        tvDateTask.text = task.data
    }
}