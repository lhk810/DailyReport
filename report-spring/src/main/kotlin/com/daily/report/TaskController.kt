package com.daily.report

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/api/task")
class TaskController(private val taskService: TaskService) {
    @GetMapping("/")
    fun getAllTasks() = taskService.getAllTasks()

    @PostMapping("/")
    fun createTask(@RequestBody payload: Task): Task =  taskService.createTask(payload)

    @PutMapping("/{targetDate}")
    fun updateTask(@PathVariable("targetDate") targetDate: String, @RequestBody payload: Task): Task =
            taskService.updateTask(targetDate, payload)

}