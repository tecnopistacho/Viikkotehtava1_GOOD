package com.example.viikkotehtava1_good

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.viikkotehtava1_good.domain.*
import com.example.viikkotehtava1_good.ui.theme.Viikkotehtava1_GOODTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikkotehtava1_GOODTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var tasks by remember { mutableStateOf(MockTasks.toMutableList()) }
                    var newTaskTitle by remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        Greeting(name = "Android")
                        Spacer(modifier = Modifier.height(16.dp))

                        // Input field for new task title
                        OutlinedTextField(
                            value = newTaskTitle,
                            onValueChange = { newTaskTitle = it },
                            label = { Text("Task title")},
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        //Add Task Button
                        Button(
                            onClick = {
                                val newTask = Task(
                                    id = tasks.size +1,
                                    title = newTaskTitle,
                                    description = "",
                                    priority = 1,
                                    dueDate = "15/01/2026",
                                    done = false
                                )
                                tasks = addTask(tasks, newTask).toMutableList()
                                newTaskTitle = ""
                            }
                        ) {
                            Text("Add Task")
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Filter by Done
                            Button(onClick = {
                                tasks = filterByDone(tasks, true).toMutableList() },
                                modifier = Modifier.weight(1f) // each button takes equal space
                            ) {
                                Text("Show Done")
                            }
                            // Sort by DueDate
                            Button(onClick = {
                                tasks = sortByDueDate(tasks).toMutableList() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Sort by Due")
                            }
                            // Show all: resets filtering/sorting and shows all tasks
                            Button(onClick = {
                                tasks = MockTasks.toMutableList() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Show All")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        // Toggle Done for each task
                        TaskListWithToggle(tasks) { taskId ->
                            tasks = toggleDone(tasks, taskId).toMutableList()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun TaskListWithToggle(tasks: List<Task>, onToggleDone: (Int) -> Unit) {
    Column {
        tasks.forEach { task ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("${task.id} - ${task.title}")
                    Text("Due: ${task.dueDate} - Done: ${task.done}")
                }
                if (!task.done) {
                    Button(onClick = { onToggleDone(task.id) }) {
                        Text("Mark Done")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

/* @Composable
fun TaskList(tasks: List<Task>) {
    Column {
        tasks.forEach { task ->
            Text("${task.id} - Title: ${task.title}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
} */


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    Viikkotehtava1_GOODTheme {
        val tasks = MockTasks
        Column(modifier = Modifier.padding(16.dp)) {
            Greeting(name = "Android")
            Spacer(modifier = Modifier.height(16.dp))
            TaskListWithToggle(tasks) { }
        }
    }
}
