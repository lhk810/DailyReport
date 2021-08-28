package com.daily.report

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface TaskRepository: JpaRepository<Task, Long?> {
    fun findAllByOrderByTargetDateDesc(): List<Task>
    fun findByTargetDate(targetDate: LocalDate): Task?
    fun existsByTargetDate(targetDate: LocalDate): Boolean
    fun deleteByTargetDate(targetDate: LocalDate): Unit
    fun findByWorkOutIsNotNullOrderByTargetDateDesc(): List<Task>
    fun findByStudyIsNotNullOrderByTargetDateDesc(): List<Task>
    fun findByHangOutIsNotNullOrderByTargetDateDesc(): List<Task>
}