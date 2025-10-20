# Quick Reference - Countdown Timer Code Snippets

## 🚀 Basic Activity Setup

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickUIPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CountdownTimerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
```
**What it does**: Sets up edge-to-edge display with Material 3 theme and Scaffold layout.

## ⏱️ Timer State Management

```kotlin
// Input state for minutes and seconds
var minutes by remember { mutableStateOf("") }
var seconds by remember { mutableStateOf("") }

// Timer state variables
var totalSeconds by remember { mutableIntStateOf(0) }
var remainingSeconds by remember { mutableIntStateOf(0) }
var isRunning by remember { mutableStateOf(false) }

// Coroutine scope for timer logic
val scope = rememberCoroutineScope()
```
**What it does**: Manages all timer-related state including input validation and running status.

## ⏰ Timer Logic with LaunchedEffect

```kotlin
// Timer countdown logic
LaunchedEffect(isRunning) {
    if (isRunning && remainingSeconds > 0) {
        while (isRunning && remainingSeconds > 0) {
            delay(1000)
            remainingSeconds--
            if (remainingSeconds == 0) {
                isRunning = false
            }
        }
    }
}
```
**What it does**: Handles the countdown logic using coroutines, decrements every second and stops when timer reaches zero.

## 🎯 Circular Progress Timer Display

```kotlin
Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.size(280.dp)
) {
    // Circular progress indicator
    CircularProgressIndicator(
        progress = {
            if (totalSeconds > 0) remainingSeconds.toFloat() / totalSeconds.toFloat()
            else 0f
        },
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 12.dp,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
        strokeCap = StrokeCap.Round,
    )

    // Time display
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTime(remainingSeconds),
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = if (remainingSeconds > 0) "remaining" else if (totalSeconds > 0) "Time's up!" else "",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
```
**What it does**: Creates a circular progress indicator with time display in the center, showing remaining time and status.

## 📝 Input Validation for Time

```kotlin
// Minutes input with digit validation
OutlinedTextField(
    value = minutes,
    onValueChange = { if (it.isEmpty() || it.all { char -> char.isDigit() }) minutes = it },
    label = { Text("Minutes") },
    modifier = Modifier.weight(1f),
    singleLine = true
)

// Seconds input with 0-59 validation
OutlinedTextField(
    value = seconds,
    onValueChange = {
        if (it.isEmpty() || (it.all { char -> char.isDigit() } && it.toIntOrNull()?.let { num -> num < 60 } == true)) {
            seconds = it
        }
    },
    label = { Text("Seconds") },
    modifier = Modifier.weight(1f),
    singleLine = true
)
```
**What it does**: Validates input to only allow digits, with seconds limited to 0-59 range.

## ⚡ Quick Preset Buttons

```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
) {
    FilledTonalButton(
        onClick = {
            minutes = "1"
            seconds = "0"
        },
        modifier = Modifier.weight(1f)
    ) {
        Text("1 min")
    }
    FilledTonalButton(
        onClick = {
            minutes = "5"
            seconds = "0"
        },
        modifier = Modifier.weight(1f)
    ) {
        Text("5 min")
    }
    FilledTonalButton(
        onClick = {
            minutes = "15"
            seconds = "0"
        },
        modifier = Modifier.weight(1f)
    ) {
        Text("15 min")
    }
}
```
**What it does**: Quick preset buttons that populate minutes/seconds fields with common timer durations.

## 🎮 Dynamic Control Buttons

```kotlin
Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp)
) {
    if (remainingSeconds == 0 && !isRunning) {
        // Start button
        Button(
            onClick = {
                val mins = minutes.toIntOrNull() ?: 0
                val secs = seconds.toIntOrNull() ?: 0
                totalSeconds = (mins * 60) + secs
                if (totalSeconds > 0) {
                    remainingSeconds = totalSeconds
                    isRunning = true
                }
            },
            modifier = Modifier
                .height(56.dp)
                .widthIn(min = 120.dp)
        ) {
            Text("START", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    } else {
        // Pause/Resume button
        Button(
            onClick = { isRunning = !isRunning },
            modifier = Modifier
                .height(56.dp)
                .widthIn(min = 120.dp)
        ) {
            Text(
                if (isRunning) "PAUSE" else "RESUME",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Reset button
        OutlinedButton(
            onClick = {
                isRunning = false
                remainingSeconds = 0
                totalSeconds = 0
                minutes = ""
                seconds = ""
            },
            modifier = Modifier
                .height(56.dp)
                .widthIn(min = 120.dp)
        ) {
            Text("RESET", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
```
**What it does**: Shows different button combinations based on timer state - Start when stopped, Pause/Resume + Reset when running.

## 🕐 Time Formatting Function

```kotlin
fun formatTime(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}
```
**What it does**: Converts seconds to MM:SS format with zero-padding for consistent display.

## 🎨 Conditional UI Rendering

```kotlin
// Show input fields only when timer is not running and at zero
if (!isRunning && remainingSeconds == 0) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Input fields here
    }
}
```
**What it does**: Conditionally shows input fields only when timer is stopped and at zero.

## 🔧 Key Imports for Timer

```kotlin
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
```
**What it does**: Essential imports for timer functionality including coroutines, Material 3, and custom styling.

## 🎯 Common Timer Patterns

### State Management
```kotlin
// Multiple related state variables
var isRunning by remember { mutableStateOf(false) }
var remainingTime by remember { mutableIntStateOf(0) }
var totalTime by remember { mutableIntStateOf(0) }
```

### Input Validation
```kotlin
// Numeric input validation
onValueChange = { 
    if (it.isEmpty() || it.all { char -> char.isDigit() }) {
        // Update value
    }
}
```

### Time Calculations
```kotlin
// Convert minutes and seconds to total seconds
val totalSeconds = (minutes * 60) + seconds

// Convert back to minutes and seconds
val mins = totalSeconds / 60
val secs = totalSeconds % 60
```

### Coroutine Timer
```kotlin
LaunchedEffect(isRunning) {
    while (isRunning && remainingTime > 0) {
        delay(1000)
        remainingTime--
    }
}
```

## 🚀 Complete Screen Layout

```kotlin
@Composable
fun CountdownTimerScreen(modifier: Modifier = Modifier) {
    // State variables here
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Countdown Timer",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        // Circular progress display
        // Input fields (conditional)
        // Control buttons
    }
}
```
**What it does**: Complete screen structure with centered layout and proper spacing.

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

**Quick Reference for Lyft Interview** - Copy-paste ready code snippets for Countdown Timer development.