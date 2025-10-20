# QuickUI Practice - Todo List with Delete Animation

A modern Android todo list application built with Jetpack Compose, featuring smooth delete animations and Material 3 design.

## 📱 Features

- **Add Tasks**: Create new todo items with a clean input interface
- **Mark Complete**: Check off completed tasks with visual feedback
- **Smooth Delete Animation**: Tasks automatically disappear with a 300ms delay after being marked complete
- **Material 3 Design**: Modern UI following Material Design guidelines
- **Lazy Loading**: Efficient rendering of todo items using LazyColumn
- **Edge-to-Edge**: Full-screen experience with proper edge-to-edge support

## 🛠️ Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design System**: Material 3
- **Architecture**: MVVM with Compose state management
- **Animations**: Compose Animation APIs
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 36 (Android 14)

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or later
- Android SDK with API 24+ installed

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd QuickUIPractice
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the project directory and select it

3. **Sync the project**
   - Android Studio will automatically sync Gradle files
   - Wait for the sync to complete

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`

## 📁 Project Structure

```
app/
├── src/main/
│   ├── java/com/example/quickuipractice/
│   │   ├── MainActivity.kt          # Main activity and UI components
│   │   └── ui/theme/                # Material 3 theme configuration
│   │       ├── Color.kt
│   │       ├── Theme.kt
│   │       └── Type.kt
│   ├── res/                         # Resources (drawables, values, etc.)
│   └── AndroidManifest.xml
├── build.gradle.kts                 # App-level build configuration
└── proguard-rules.pro
```

## 🎨 Key Components

### MainActivity.kt
- **TodoItem**: Data class representing a todo item with id, title, and completion status
- **TodoListScreen**: Main composable containing the entire todo list interface
- **TodoItemRow**: Individual todo item component with checkbox and text

### Animation Implementation
The app features a smooth delete animation where:
1. User checks off a todo item
2. The item is marked as completed
3. After a 300ms delay, the item is removed from the list
4. The removal is animated using Compose's built-in LazyColumn animations

## 🔧 Configuration

### Build Configuration
- **Compile SDK**: 36
- **Min SDK**: 24
- **Target SDK**: 36
- **Java Version**: 11
- **Kotlin Version**: 2.0.21

### Dependencies
- **Jetpack Compose BOM**: 2024.09.00
- **Material 3**: Latest stable
- **Activity Compose**: 1.8.0
- **Lifecycle Runtime**: 2.6.1

## 🎯 Usage

1. **Adding Tasks**:
   - Type your task in the text field
   - Tap the "+" floating action button
   - The task will appear in the list below

2. **Completing Tasks**:
   - Tap the checkbox next to any task
   - The task will be marked as completed
   - After a brief delay, it will smoothly disappear from the list

3. **Default Tasks**:
   - The app starts with 3 sample tasks:
     - "Learn Jetpack Compose"
     - "Build a todo app"
     - "Practice Kotlin"

## 🎨 Customization

### Adding New Features
- **Persistence**: Add Room database for data persistence
- **Categories**: Implement task categorization
- **Due Dates**: Add date/time picker for task deadlines
- **Search**: Add search functionality for tasks
- **Sorting**: Implement sorting by date, priority, or completion status

### Styling
- Modify `ui/theme/Color.kt` for custom color schemes
- Update `ui/theme/Type.kt` for custom typography
- Adjust `ui/theme/Theme.kt` for overall theme configuration

## 🐛 Troubleshooting

### Common Issues

1. **Build Errors**:
   - Ensure you're using the correct Android Studio version
   - Clean and rebuild the project (`Build > Clean Project`)

2. **Animation Not Working**:
   - Check that the `animateItem()` modifier is properly implemented
   - Verify Compose animation dependencies are included

3. **App Crashes**:
   - Check device/emulator meets minimum API level (24)
   - Review logcat for specific error messages

## 📝 Development Notes

- The app uses `mutableStateListOf` for state management
- LazyColumn with `key = { it.id }` ensures proper animation behavior
- Coroutines are used for delayed task removal
- Material 3 components provide consistent theming

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🙏 Acknowledgments

- Jetpack Compose team for the amazing UI framework
- Material Design team for the design system
- Android team for the development tools and platform

---

**QuickUI Practice** - A simple yet elegant todo list app demonstrating modern Android development with Jetpack Compose.