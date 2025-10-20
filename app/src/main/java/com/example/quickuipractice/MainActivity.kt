package com.example.quickuipractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.quickuipractice.ui.theme.QuickUIPracticeTheme

// Extension function for smooth item animations
fun Modifier.animateItem() = this.animateContentSize(
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
)

data class TodoItem(
    val id: Int,
    val title: String,
    var isCompleted: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickUIPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TodoListScreen(modifier: Modifier = Modifier) {
    var todoText by remember { mutableStateOf("") }
    val todoList = remember {
        mutableStateListOf(
            TodoItem(1, "Learn Jetpack Compose", false),
            TodoItem(2, "Build a todo app", false),
            TodoItem(3, "Practice Kotlin", false)
        )
    }
    var nextId by remember { mutableStateOf(4) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Todo List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input field and Add button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = todoText,
                onValueChange = { todoText = it },
                label = { Text("New task") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            FloatingActionButton(
                onClick = {
                    if (todoText.isNotBlank()) {
                        todoList.add(TodoItem(nextId++, todoText.trim()))
                        todoText = ""
                    }
                },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add task")
            }
        }

        // Todo list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todoList, key = { it.id }) { todo ->
                TodoItemRow(
                    todo = todo,
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            val index = todoList.indexOf(todo)
                            if (index != -1) {
                                todoList[index] = todo.copy(isCompleted = true)
                                scope.launch {
                                    delay(300)
                                    todoList.removeAt(index)
                                }
                            }
                        }
                    },
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}

@Composable
fun TodoItemRow(
    todo: TodoItem,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    QuickUIPracticeTheme {
        TodoListScreen()
    }
}