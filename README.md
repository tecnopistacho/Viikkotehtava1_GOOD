# Kotlin basics

This repository contains a simple **Android to-do list app** built with **Kotlin** and **Jetpack Compose**.  
Users can **view tasks, add new tasks, mark tasks as done, filter, and sort tasks by due date**.

---

## Features

- Display a list of tasks
- **Add new tasks** and give it a title
- **Mark tasks as done** (only undone tasks can be marked)  
- **Filter tasks** to show only completed ones (Show Done Button) 
- **Sort tasks** by due date (Sort by Due Button)
- Reset the task list to show all tasks (Show All Button)

---

## Data Model and Functions

```kotlin
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: String,
    val done: Boolean
)```


---

## Screenshots

*(Optional: add screenshots here if you have them)*

---

## Dependencies

- Kotlin
- Jetpack Compose
- Material3
- AndroidX Libraries

---

## Future Improvement Ideas

- Edit task title, description, due date, and priority
- Delete task
- Sort by priority
- Change task colour by priority level, e.g. priority 1 = colour red
- Improve UI with colors, icons, and animations

---

