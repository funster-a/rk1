package com.example.rk1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
class AddTaskActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var deadlineEditText: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var saveButton: Button

    private var isEditMode = false
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        Log.d("Lifecycle", "AddTaskActivity - onCreate")

        initViews()
        setupSpinner()
        checkEditMode()
        setupSaveButton()
    }

    private fun initViews() {
        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        deadlineEditText = findViewById(R.id.deadlineEditText)
        prioritySpinner = findViewById(R.id.prioritySpinner)
        saveButton = findViewById(R.id.saveButton)
    }

    private fun setupSpinner() {
        val priorities = arrayOf("Высокий", "Средний", "Низкий")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = adapter
    }

    private fun checkEditMode() {
        taskId = intent.getIntExtra("TASK_ID", -1)
        if (taskId != -1) {
            isEditMode = true
            val title = intent.getStringExtra("TASK_TITLE") ?: ""
            val description = intent.getStringExtra("TASK_DESCRIPTION") ?: ""
            val deadline = intent.getStringExtra("TASK_DEADLINE") ?: ""
            val priority = intent.getStringExtra("TASK_PRIORITY") ?: "Средний"

            // Remove "Описание: " prefix if present
            val cleanDescription = description.removePrefix("Описание: ")
            val cleanDeadline = deadline.removePrefix("Срок выполнения: ")
            val cleanPriority = priority.removePrefix("Приоритет: ")

            titleEditText.setText(title)
            descriptionEditText.setText(cleanDescription)
            deadlineEditText.setText(cleanDeadline)

            val priorities = arrayOf("Высокий", "Средний", "Низкий")
            val priorityIndex = priorities.indexOf(cleanPriority)
            if (priorityIndex >= 0) {
                prioritySpinner.setSelection(priorityIndex)
            }
        }
    }

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val deadline = deadlineEditText.text.toString()
            val priority = prioritySpinner.selectedItem.toString()

            // Kotlin: основы - проверка условий
            if (title.isBlank() || description.isBlank() || deadline.isBlank()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                if (isEditMode) {
                    putExtra("TASK_ID", taskId)
                }
                putExtra("TITLE", title)
                putExtra("DESCRIPTION", description)
                putExtra("DEADLINE", deadline)
                putExtra("PRIORITY", priority)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}