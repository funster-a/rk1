package com.example.rk1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    val taskList = MutableLiveData<MutableList<Task>>(mutableListOf())
    private val taskMap = HashMap<Int, Task>()
    private var nextId = 1

    fun addTask(title: String, description: String, deadline: String, priority: String) {
        val newTask = Task(nextId, title, description, deadline, priority)
        val currentList = taskList.value ?: mutableListOf()
        currentList.add(newTask)
        taskList.value = currentList
        taskMap[nextId] = newTask
        nextId++
    }

    fun updateTask(taskId: Int, title: String, description: String, deadline: String, priority: String) {
        val task = taskMap[taskId]
        task?.let {
            it.title = title
            it.description = description
            it.deadline = deadline
            it.priority = priority
            // Post the updated list to LiveData
            taskList.value = taskList.value
        }
    }

    fun deleteTask(taskId: Int) {
        val task = taskMap[taskId]
        task?.let {
            val currentList = taskList.value ?: mutableListOf()
            currentList.remove(it)
            taskList.value = currentList
            taskMap.remove(taskId)
        }
    }

    fun findTaskById(taskId: Int): Task? {
        return taskMap[taskId]
    }
}