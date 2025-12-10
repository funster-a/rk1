package com.example.rk1

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: MutableList<Task>,
    private val onItemClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.taskTitle)
        val deadlineTextView: TextView = itemView.findViewById(R.id.taskDeadline)
        val priorityTextView: TextView = itemView.findViewById(R.id.taskPriority)
        val taskCompleted: CheckBox = itemView.findViewById(R.id.taskCompleted)
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
        holder.taskCompleted.isChecked = task.isCompleted

        // Устанавливаем цвет приоритета
        val priorityColor = when (task.priority) {
            "Высокий" -> Color.RED
            "Средний" -> Color.BLUE
            else -> Color.GRAY
        }
        holder.priorityTextView.setTextColor(priorityColor)
        
        if (task.isCompleted) {
            holder.titleTextView.paintFlags = holder.titleTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.deadlineTextView.paintFlags = holder.deadlineTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.titleTextView.paintFlags = holder.titleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.deadlineTextView.paintFlags = holder.deadlineTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.itemView.setOnClickListener {
            onItemClick(task)
        }
        
        holder.taskCompleted.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = tasks.size
}