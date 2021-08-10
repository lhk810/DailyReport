package com.daily.report

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getAllTasks(): List<Task> = taskRepository.findAllByOrderByTargetDateDesc()

    fun getTask(targetDate: String): Task? =
            taskRepository.findByTargetDate(LocalDate.parse(targetDate, DateTimeFormatter.ISO_DATE))

    fun createTask(task: Task): Task {
        task.targetDate = task.targetDate ?: LocalDate.now() //set default
        if (taskRepository.existsByTargetDate(task.targetDate!!)) {
            throw TaskAlreadyExistException()
        }
        taskRepository.save(task)
        return task
    }

    fun updateTask(targetDate: String, task: Task): Task {
        val convertedDate: LocalDate = LocalDate.parse(targetDate, DateTimeFormatter.ISO_DATE)
        val origin: Task? = taskRepository.findByTargetDate(convertedDate) ?:
        throw TaskNotFoundException()

        task.id = origin?.id
        task.targetDate = convertedDate
        task.workOut = task.workOut ?: origin?.workOut
        task.hangOut = task.hangOut ?: origin?.hangOut
        task.study = task.study ?: origin?.study
        taskRepository.save(task)

        return task
    }

    @Transactional
    fun deleteTask(targetDate: String): Unit =
            taskRepository.deleteByTargetDate(LocalDate.parse(targetDate, DateTimeFormatter.ISO_DATE))
}

