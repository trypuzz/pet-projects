package com.example.notes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.TaskItemBinding

class TaskViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val binding = TaskItemBinding.bind(item)

    fun bind(task: Task) = with(binding){
        tvNameTask.text = task.nameTask
        tvMessageTask.text = task.messageTask
        tvDateTask.text = task.data
    }
}