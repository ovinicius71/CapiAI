# 📱 Intelligent Question-Solving App

## 📌 Overview
This project aims to develop an innovative mobile application for solving **Mathematics, Physics, and Computing** problems, covering everything from basic to advanced challenges. The system utilizes **Artificial Intelligence** to provide detailed and accurate solutions, making it a valuable tool for students and professionals in these fields.

## ⚙️ Technologies Used
- **Jetpack Compose** - Modern framework for a responsive and fluid interface
- **Modified DeepSeek** - AI model specifically trained for these domains
- **Kotlin** - Main language for app development
- **TensorFlow Lite** - For model optimization and efficient execution on mobile devices
- **Room Database** - For local storage of question history
- **Firebase** - For cloud synchronization and potential collaborative expansion

## 🔥 Key Features
✅ **Automatic Question Solving** - Processes mathematical, physical, and computational problems with step-by-step solutions.  
✅ **Detailed Explanations** - Each answer is accompanied by a well-structured and intuitive reasoning process.  
✅ **Numerical and Algebraic Validation** - Ensures results are accurate before displaying them to users.  
✅ **Modern and Responsive Interface** - Designed with **Jetpack Compose**, optimizing usability.  
✅ **Question History** - Users can access previously solved problems.  
✅ **Offline Execution** - Thanks to model optimization, part of the processing occurs on the device.  
✅ **Support for Future Expansions** - Possibility to add features such as voice-based solving and image analysis.

## 📖 Project Structure
```
📂 project-root/
   ├── 📂 app/              # Main application module
   │   ├── 📂 src/
   │   │   ├── 📂 main/
   │   │   │   ├── 📂 java/com/example/app/  # Kotlin source code
   │   │   │   ├── 📂 res/                   # Resources (layouts, drawables, etc.)
   │   │   │   ├── 📜 AndroidManifest.xml   # Android configuration file
   │   │   ├── 📂 test/                     # Unit tests
   │   │   ├── 📂 androidTest/              # Instrumentation tests
   │   ├── 📜 build.gradle.kts              # Gradle build script for the app module
   ├── 📂 ml/               # AI model and optimizations
   ├── 📂 network/          # API and Firebase integration
   ├── 📂 utils/            # Auxiliary functions and utilities
   ├── 📜 build.gradle.kts  # Project-level Gradle build script
   ├── 📜 settings.gradle.kts # Project settings
   ├── 📜 gradle.properties  # Global Gradle properties
```

## 🚀 How to Run
1. Clone the repository:
   ```sh
   git clone https://github.com/ovinicius71/CapiAI.git
   ```
2. Open the project in Android Studio.
3. Run it on an emulator or physical device.

## 📌 Future Enhancements
- 🔹 Support for **question input via camera (OCR)**
- 🔹 Implementation of a **collaborative mode**, allowing users to share solutions
- 🔹 Enhancement of the **AI model** with active learning
- 🔹 Integration with virtual assistants for voice interactions

## 📜 License
This project is licensed under the MIT License. Feel free to contribute and expand!

---

