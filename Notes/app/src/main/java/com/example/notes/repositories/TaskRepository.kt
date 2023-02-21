package com.example.notes.repositories

import com.example.notes.dataBase.TaskDataBase
import com.example.notes.model.TaskEntity

class TaskRepository {


    suspend fun addTasks(task: TaskEntity) {
        TaskDataBase.db.taskDao().insertTask(task)
    }

    suspend fun deleteTask(id: Int) {
        TaskDataBase.db.taskDao().deleteTask(id)
    }

    suspend fun deleteAllListTasks(){
        return TaskDataBase.db.taskDao().deleteAllTask()
    }

    suspend fun getListTasks(): ArrayList<TaskEntity> {
        return TaskDataBase.db.taskDao().selectAllTask() as ArrayList<TaskEntity>
    }
}
