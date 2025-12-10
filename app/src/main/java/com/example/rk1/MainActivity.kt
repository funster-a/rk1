package com.example.rk1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var addTaskFab: FloatingActionButton
    private lateinit var emptyView: TextView
    private lateinit var adapter: TaskAdapter

    private val viewModel: TaskViewModel by viewModels()

    private val addTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val title = data?.getStringExtra("TITLE") ?: ""
            val description = data?.getStringExtra("DESCRIPTION") ?: ""
            val deadline = data?.getStringExtra("DEADLINE") ?: ""
            val priority = data?.getStringExtra("PRIORITY") ?: ""
            viewModel.addTask(title, description, deadline, priority)
        }
    }

    private val taskDetailLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val taskId = data?.getIntExtra("TASK_ID", -1) ?: -1

            if (data?.hasExtra("TASK_TITLE") == true) { // Task was edited
                val title = data.getStringExtra("TASK_TITLE") ?: ""
                val description = data.getStringExtra("TASK_DESCRIPTION") ?: ""
                val deadline = data.getStringExtra("TASK_DEADLINE") ?: ""
                val priority = data.getStringExtra("TASK_PRIORITY") ?: ""
                viewModel.updateTask(taskId, title, description, deadline, priority)
            } else { // Task was deleted
                viewModel.deleteTask(taskId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        initViews()
        setupRecyclerView()
        setupClickListeners()

        viewModel.taskList.observe(this, Observer {
            adapter = TaskAdapter(it) { task ->
                val intent = Intent(this, TaskDetailActivity::class.java).apply {
                    putExtra("TASK_ID", task.id)
                    putExtra("TASK_TITLE", task.title)
                    putExtra("TASK_DESCRIPTION", task.description)
                    putExtra("TASK_DEADLINE", task.deadline)
                    putExtra("TASK_PRIORITY", task.priority)
                }
                taskDetailLauncher.launch(intent)
            }
            tasksRecyclerView.adapter = adapter
            checkEmptyView()
        })
    }

    private fun initViews() {
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView)
        addTaskFab = findViewById(R.id.addTaskFab)
        emptyView = findViewById(R.id.emptyView)
    }

    private fun setupRecyclerView() {
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupClickListeners() {
        addTaskFab.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            addTaskLauncher.launch(intent)
        }
    }

    private fun checkEmptyView() {
        if (viewModel.taskList.value?.isEmpty() == true) {
            tasksRecyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            tasksRecyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}