# Todo List Compose
<br />
<p align="center">
  <p align="center">
    Simple ToDo list app, made in Compose
  </p>
</p>

<p align="row">
  <p align="center">
    <img src= "https://i.imgur.com/Fi4dl5V.jpg" width="200" >
    <img src= "https://i.imgur.com/DqSs3JM.jpg" width="200" >
    <img src= "https://i.imgur.com/DK3Dv9h.jpg" width="200" >
   </p>
   <p align="center">
    <img src= "https://i.imgur.com/my0V2L3.jpg" width="200" >
    <img src= "https://i.imgur.com/Cg7tlRM.jpg" width="200" >
    <img src= "https://i.imgur.com/NVTI2QU.jpg" width="200" >
   </p>
</p>

## Used in this project

- [x] Compose
- [x] Room
- [x] Coroutines
- [x] Hilt  
- [x] MVVM pattern 

## Features

- [x] ToDo tasks arranged in a list
- [x] Add new tasks
- [x] Delete tasks
- [x] Restore deleted tasks
- [x] Edit existing tasks


## Requirements

- Android 8.0 or higher

## Installation

#### APK

APK file of this application is available in releases tab

## Usage example

Launching the app gives you empty list that will contain the tasks you add. To add a task, click on a floating button in the right corner. You will be presented with
a new screen. You have to fill in the title, description is optional (you're informed about that when trying to save the task). After saving your new task, it will appear 
in the task list. From there you have multiple options. You can:
- mark task as finished, clicking on a empty checkbox (or mark as unfinished clicking on it again)
- edit your task
- delete task
- restore deleted task by clicking "Go back" on the bottom snackbar, after deleting the task
