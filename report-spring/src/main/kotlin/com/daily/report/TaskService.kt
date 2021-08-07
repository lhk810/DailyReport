package com.daily.report

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getAllTasks(): List<Task> = taskRepository.findAllByOrderByTargetDate()

    fun createTask(task: Task): Task {
        task.targetDate = LocalDate.now()
        taskRepository.save(task)
        return task
    }
}