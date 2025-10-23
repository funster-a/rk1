package com.example.rk1

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var taskTitle: TextView
    private lateinit var taskDescription: TextView
    private lateinit var taskDeadline: TextView
    private lateinit var taskPriority: TextView

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
        // Тема: Multiple Activities and Intents - получение данных
        val taskId = intent.getIntExtra("TASK_ID", -1)
        val title = intent.getStringExtra("TASK_TITLE") ?: "Без названия"
        val description = intent.getStringExtra("TASK_DESCRIPTION") ?: "Нет описания"
        val deadline = intent.getStringExtra("TASK_DEADLINE") ?: "Не указан"
        val priority = intent.getStringExtra("TASK_PRIORITY") ?: "Средний"

        taskTitle.text = title
        taskDescription.text = "Описание: $description"
        taskDeadline.text = "Срок выполнения: $deadline"
        taskPriority.text = "Приоритет: $priority"

        // Устанавливаем цвет приоритета
        val priorityColor = when (priority) {
            "Высокий" -> Color.RED
            "Средний" -> Color.BLUE
            else -> Color.GRAY
        }
        taskPriority.setTextColor(priorityColor)
    }
}