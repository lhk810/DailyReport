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
        taskRepository.save(task) // handle exception later
        return task
    }

    fun updateTask(targetDate: String, task: Task): Task {
        val convertedDate: LocalDate = LocalDate.parse(targetDate, DateTimeFormatter.ISO_DATE)
        val origin: Task? = taskRepository.findByTargetDate(convertedDate) ?:
        throw TaskStateException(HttpStatus.NOT_FOUND, "No task at the date")

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

class TaskStateException(notFound: HttpStatus, message: String) : Throwable() {

}