package com.example.rk1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val database = TaskDatabase.getDatabase(application)
    private val taskDao = database.taskDao()

    // Конвертируем Flow<TaskEntity> в LiveData<List<Task>>
    val taskList: LiveData<List<Task>> = taskDao.getAllTasks()
        .map { entities -> entities.map { it.toTask() } }
        .asLiveData()

    init {
        // Загружаем задачи при инициализации
        loadTasks()
    }

    private fun loadTasks() {
        // Загрузка происходит автоматически через Flow
    }

    fun addTask(title: String, description: String, deadline: String, priority: String) {
        viewModelScope.launch {
            val newTask = TaskEntity(
                title = title,
                description = description,
                deadline = deadline,
                priority = priority,
                isCompleted = false
            )
            taskDao.insertTask(newTask)
        }
    }

    fun updateTask(taskId: Int, title: String, description: String, deadline: String, priority: String) {
        viewModelScope.launch {
            val existingTask = taskDao.getTaskById(taskId)
            existingTask?.let {
                val updatedTask = it.copy(
                    title = title,
                    description = description,
                    deadline = deadline,
                    priority = priority
                )
                taskDao.updateTask(updatedTask)
            }
        }
    }

    fun updateTaskCompletion(taskId: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            val existingTask = taskDao.getTaskById(taskId)
            existingTask?.let {
                val updatedTask = it.copy(isCompleted = isCompleted)
                taskDao.updateTask(updatedTask)
            }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            taskDao.deleteTaskById(taskId)
        }
    }

    fun findTaskById(taskId: Int): Task? {
        // Для синхронного доступа используем runBlocking (не рекомендуется в production)
        // В данном случае лучше использовать LiveData или Flow
        return null // Можно реализовать через LiveData если нужно
    }
}

// Extension функции для конвертации между Entity и Domain моделью
private fun TaskEntity.toTask(): Task {
    return Task(
        id = this.id,
        title = this.title,
        description = this.description,
        deadline = this.deadline,
        priority = this.priority,
        isCompleted = this.isCompleted
    )
}

private fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        deadline = this.deadline,
        priority = this.priority,
        isCompleted = this.isCompleted
    )
}
