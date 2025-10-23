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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        Log.d("Lifecycle", "AddTaskActivity - onCreate")

        initViews()
        setupSpinner()
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