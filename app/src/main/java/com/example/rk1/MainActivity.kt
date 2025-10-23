package com.example.rk1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var addTaskButton: Button
    private val taskList = ArrayList<Task>()
    private lateinit var adapter: TaskAdapter

    // Используем HashMap для хранения задач по ID (тема: Коллекции Kotlin)
    private val taskMap = HashMap<Int, Task>()

    // Современный способ обработки результата активности
    private val addTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val title = data?.getStringExtra("TITLE") ?: ""
            val description = data?.getStringExtra("DESCRIPTION") ?: ""
            val deadline = data?.getStringExtra("DEADLINE") ?: ""
            val priority = data?.getStringExtra("PRIORITY") ?: "Средний"

            val newId = taskList.size + 1
            val newTask = Task(newId, title, description, deadline, priority)

            taskList.add(newTask)
            taskMap[newId] = newTask
            adapter.notifyItemInserted(taskList.size - 1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Lifecycle", "MainActivity - onCreate") // Тема: Жизненный цикл

        initViews()
        setupRecyclerView()
        loadSampleData()
        setupClickListeners()
    }

    private fun initViews() {
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView)
        addTaskButton = findViewById(R.id.addTaskButton)
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(taskList) { task ->
            // Тема: Multiple Activities and Intents
            val intent = Intent(this, TaskDetailActivity::class.java).apply {
                putExtra("TASK_ID", task.id)
                putExtra("TASK_TITLE", task.title)
                putExtra("TASK_DESCRIPTION", task.description)
                putExtra("TASK_DEADLINE", task.deadline)
                putExtra("TASK_PRIORITY", task.priority)
            }
            startActivity(intent)
        }
        tasksRecyclerView.adapter = adapter
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadSampleData() {
        // Добавляем примеры задач
        val sampleTasks = arrayOf(
            Task(1, "Сделать лабораторную работу", "Завершить проект по Android", "2024-01-15", "Высокий"),
            Task(2, "Купить продукты", "Молоко, хлеб, яйца", "2024-01-10", "Средний"),
            Task(3, "Прочитать книгу", "Kotlin Programming", "2024-01-20", "Низкий")
        )

        // Используем HashSet для уникальных ID (тема: Коллекции Kotlin)
        val idSet = HashSet<Int>()

        sampleTasks.forEach { task ->
            taskList.add(task)
            taskMap[task.id] = task
            idSet.add(task.id)
        }

        adapter.notifyDataSetChanged()
    }

    private fun setupClickListeners() {
        addTaskButton.setOnClickListener {
            // Тема: Multiple Activities and Intents
            val intent = Intent(this, AddTaskActivity::class.java)
            addTaskLauncher.launch(intent)
        }
    }

    // Тема: Жизненный цикл активности
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "MainActivity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "MainActivity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "MainActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "MainActivity - onDestroy")
    }

    companion object {
        const val ADD_TASK_REQUEST = 1
    }
}