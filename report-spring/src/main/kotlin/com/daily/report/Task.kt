package com.daily.report

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Task(targetDate: LocalDate?, workOut: String?, study: String?, hangOut:String?) {
    @Id
    @GeneratedValue
    var id: Long? = null
    @Column(unique = true, nullable = false)
    var targetDate: LocalDate? = targetDate
    var workOut: String? = workOut
    var study: String? = study
    var hangOut: String? = hangOut
}