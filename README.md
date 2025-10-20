# Quick Reference - Jetpack Compose Code Snippets

## 🚀 Basic Activity Setup

```kotlin
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
```
**What it does**: Sets up edge-to-edge display with Material 3 theme and Scaffold layout.

## 📱 State Management

```kotlin
// Mutable state for text input
var todoText by remember { mutableStateOf("") }

// Mutable state list for data
val todoList = remember {
    mutableStateListOf(
        TodoItem(1, "Learn Jetpack Compose", false),
        TodoItem(2, "Build a todo app", false)
    )
}

// Counter state
var nextId by remember { mutableStateOf(4) }

// Coroutine scope for animations
val scope = rememberCoroutineScope()
```
**What it does**: Manages UI state that triggers recomposition when changed.

## 🎨 Data Class

```kotlin
data class TodoItem(
    val id: Int,
    val title: String,
    var isCompleted: Boolean = false
)
```
**What it does**: Immutable data class with default parameter for completion status.

## 📝 Text Input with FAB

```kotlin
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
```
**What it does**: Input field with floating action button that adds items to list and clears input.

## 📋 LazyColumn with Items

```kotlin
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
```
**What it does**: Efficiently renders list items with key-based animations and delayed removal.

## 🎯 Individual List Item

```kotlin
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
```
**What it does**: Card-based list item with checkbox and text, using Material 3 elevation.

## ✨ Animation Extension

```kotlin
fun Modifier.animateItem() = this.animateContentSize(
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
)
```
**What it does**: Smooth content size animation for list item removal with easing curve.

## 🎨 Material 3 Theme Setup

```kotlin
@Composable
fun QuickUIPracticeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```
**What it does**: Applies Material 3 theming with automatic dark/light mode detection.

## 🔧 Key Imports

```kotlin
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
```
**What it does**: Essential imports for Compose UI, animations, and coroutines.

## 🎯 Common Patterns

### State Hoisting
```kotlin
// Pass state down as parameters
@Composable
fun ParentComponent() {
    var state by remember { mutableStateOf(initialValue) }
    ChildComponent(
        value = state,
        onValueChange = { state = it }
    )
}
```

### Conditional Rendering
```kotlin
if (condition) {
    Text("Show this")
} else {
    Text("Show that")
}
```

### List Operations
```kotlin
// Add item
list.add(newItem)

// Remove item
list.removeAt(index)

// Update item
list[index] = item.copy(property = newValue)

// Find item
val index = list.indexOf(item)
```

## 🚀 Quick Build Config

```kotlin
android {
    namespace = "com.example.quickuipractice"
    compileSdk = 36
    
    defaultConfig {
        applicationId = "com.example.quickuipractice"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    
    buildFeatures {
        compose = true
    }
}
```

## 📱 Essential Dependencies

```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
}
```

---

**Quick Reference for Lyft Interview** - Copy-paste ready code snippets for Jetpack Compose development.