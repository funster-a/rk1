package com.example.rk1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: List<Task>,
    private val onItemClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.taskTitle)
        val deadlineTextView: TextView = itemView.findViewById(R.id.taskDeadline)
        val priorityTextView: TextView = itemView.findViewById(R.id.taskPriority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.titleTextView.text = task.title
        holder.deadlineTextView.text = "Срок: ${task.deadline}"
        holder.priorityTextView.text = task.priority

        // Устанавливаем цвет приоритета
        val priorityColor = when (task.priority) {
            "Высокий" -> Color.RED
            "Средний" -> Color.BLUE
            else -> Color.GRAY
        }
        holder.priorityTextView.setTextColor(priorityColor)

        holder.itemView.setOnClickListener {
            onItemClick(task)
        }
    }

    override fun getItemCount(): Int = tasks.size
}