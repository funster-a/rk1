package com.example.rk1

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var taskTitle: TextView
    private lateinit var taskDescription: TextView
    private lateinit var taskDeadline: TextView
    private lateinit var taskPriority: TextView

    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        Log.d("Lifecycle", "TaskDetailActivity - onCreate")

        initViews()
        displayTaskDetails()
    }

    private fun initViews() {
        taskTitle = findViewById(R.id.taskTitle)
        taskDescription = findViewById(R.id.taskDescription)
        taskDeadline = findViewById(R.id.taskDeadline)
        taskPriority = findViewById(R.id.taskPriority)
    }

    private fun displayTaskDetails() {
        taskId = intent.getIntExtra("TASK_ID", -1)
        val title = intent.getStringExtra("TASK_TITLE") ?: "Без названия"
        val description = intent.getStringExtra("TASK_DESCRIPTION") ?: "Нет описания"
        val deadline = intent.getStringExtra("TASK_DEADLINE") ?: "Не указан"
        val priority = intent.getStringExtra("TASK_PRIORITY") ?: "Средний"

        taskTitle.text = title
        taskDescription.text = "Описание: $description"
        taskDeadline.text = "Срок выполнения: $deadline"
        taskPriority.text = "Приоритет: $priority"

        val priorityColor = when (priority) {
            "Высокий" -> Color.RED
            "Средний" -> Color.BLUE
            else -> Color.GRAY
        }
        taskPriority.setTextColor(priorityColor)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                editTask()
                true
            }
            R.id.action_delete -> {
                deleteTask()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun editTask() {
        val intent = Intent(this, AddTaskActivity::class.java).apply {
            putExtra("TASK_ID", taskId)
            putExtra("TASK_TITLE", taskTitle.text.toString())
            putExtra("TASK_DESCRIPTION", taskDescription.text.toString())
            putExtra("TASK_DEADLINE", taskDeadline.text.toString())
            putExtra("TASK_PRIORITY", taskPriority.text.toString())
        }
        startActivityForResult(intent, EDIT_TASK_REQUEST)
    }

    private fun deleteTask() {
        val resultIntent = Intent()
        resultIntent.putExtra("TASK_ID", taskId)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    companion object {
        const val EDIT_TASK_REQUEST = 2
    }
}