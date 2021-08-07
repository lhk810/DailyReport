package com.daily.report

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TaskRepository: JpaRepository<Task, Long?> {
    fun findAllByOrderByTargetDate(): List<Task>
}