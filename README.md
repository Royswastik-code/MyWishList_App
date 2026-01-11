# My Wish List App

My Wish List App is a modern Android application built end-to-end using **Kotlin** and **Jetpack Compose**. The app enables users to create, manage, and persist their personal wish lists using a **Room database**, ensuring reliable offline data storage.

---

## ✨ Features

- Add, update, and delete wishlist items
- Persistent local storage using Room Database
- Modern UI built entirely with Jetpack Compose
- Offline-first functionality
- Clean and scalable MVVM architecture
- Smooth state-driven UI updates

---

## 🛠 Tech Stack

- **Language:** Kotlin  
- **UI Toolkit:** Jetpack Compose  
- **Database:** Room  
- **Architecture:** MVVM  
- **State Management:** ViewModel + Compose State  
- **Build System:** Gradle  

---

## 🏗 Architecture Overview

The application follows the **MVVM (Model–View–ViewModel)** architecture to ensure separation of concerns and maintainability.

- **UI Layer:** Jetpack Compose composables and screens  
- **ViewModel Layer:** Handles UI state and business logic  
- **Repository Layer:** Acts as a single source of truth  
- **Data Layer:** Room Database (Entity, DAO, Database)  

---

## 📂 Project Structure

com.example.mywishlistapp
│
├── data
│ ├── entity
│ ├── dao
│ └── database
│
├── repository
│
├── ui
│ ├── screens
│ ├── components
│ └── theme
│
├── viewmodel
│
└── MainActivity.kt


---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Android SDK 24+
- Kotlin 1.9+

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/my-wish-list-app.git

then -> Open the project in Android Studio ->Sync Gradle files -> Run the app on an emulator or physical device
