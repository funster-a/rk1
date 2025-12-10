package com.example.rk1

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var taskTitle: TextView
    private lateinit var taskDescription: TextView
    private lateinit var taskDeadline: TextView
    private lateinit var taskPriority: TextView

    private var taskId: Int = -1
    private var currentTitle: String = ""
    private var currentDescription: String = ""
    private var currentDeadline: String = ""
    private var currentPriority: String = ""

    private val editTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val editedTaskId = data?.getIntExtra("TASK_ID", -1) ?: -1
            if (editedTaskId == taskId) {
                val title = data?.getStringExtra("TITLE") ?: ""
                val description = data?.getStringExtra("DESCRIPTION") ?: ""
                val deadline = data?.getStringExtra("DEADLINE") ?: ""
                val priority = data?.getStringExtra("PRIORITY") ?: ""

                val resultIntent = Intent().apply {
                    putExtra("TASK_ID", taskId)
                    putExtra("TASK_TITLE", title)
                    putExtra("TASK_DESCRIPTION", description)
                    putExtra("TASK_DEADLINE", deadline)
                    putExtra("TASK_PRIORITY", priority)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }

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
        currentTitle = intent.getStringExtra("TASK_TITLE") ?: "Без названия"
        currentDescription = intent.getStringExtra("TASK_DESCRIPTION") ?: "Нет описания"
        currentDeadline = intent.getStringExtra("TASK_DEADLINE") ?: "Не указан"
        currentPriority = intent.getStringExtra("TASK_PRIORITY") ?: "Средний"

        taskTitle.text = currentTitle
        taskDescription.text = "Описание: $currentDescription"
        taskDeadline.text = "Срок выполнения: $currentDeadline"
        taskPriority.text = "Приоритет: $currentPriority"

        val priorityColor = when (currentPriority) {
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
            putExtra("TASK_TITLE", currentTitle)
            putExtra("TASK_DESCRIPTION", currentDescription)
            putExtra("TASK_DEADLINE", currentDeadline)
            putExtra("TASK_PRIORITY", currentPriority)
        }
        editTaskLauncher.launch(intent)
    }

    private fun deleteTask() {
        val resultIntent = Intent()
        resultIntent.putExtra("TASK_ID", taskId)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}