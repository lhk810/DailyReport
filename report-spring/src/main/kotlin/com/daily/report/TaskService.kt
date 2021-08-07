package com.daily.report

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getAllTasks(): List<Task> = taskRepository.findAllByOrderByTargetDateDesc()

    fun getTask(targetDate: LocalDate): Task? = taskRepository.findByTargetDate(targetDate)

    fun createTask(task: Task): Task {
        task.targetDate = task.targetDate ?: LocalDate.now() //set default
        taskRepository.save(task)
        return task
    }
}