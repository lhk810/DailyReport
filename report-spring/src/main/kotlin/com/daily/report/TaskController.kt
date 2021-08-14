package com.daily.report

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/task")
class TaskController(private val taskService: TaskService) {
    @GetMapping("/")
    fun getAllTasks() =
            taskService.getAllTasks()

    @GetMapping("/{targetDate}")
    fun getTask(@PathVariable("targetDate") targetDate: String): Task? =
            taskService.getTask(targetDate)

    @PostMapping("/")
    fun createTask(@RequestBody payload: Task): Task {
        try {
            return taskService.createTask(payload)
        } catch (ex: TaskAlreadyExistException) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Task already exists at the date", ex)
        }
    }

    @PutMapping("/{targetDate}")
    fun updateTask(@PathVariable("targetDate") targetDate: String, @RequestBody payload: Task): Task {
        try {
            return taskService.updateTask(targetDate, payload)
        } catch (ex: TaskNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No task at the date", ex)
        }
    }


    @DeleteMapping("/{targetDate}")
    fun deleteTask(@PathVariable("targetDate") targetDate: String): Unit =
            taskService.deleteTask(targetDate)
}