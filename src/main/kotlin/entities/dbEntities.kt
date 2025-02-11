package com.r4men.entities

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table


object Users : IntIdTable() {
    val login = varchar("login", 50).uniqueIndex() // Логин как первичный ключ
    val name = varchar("name", 50)
    val surname = varchar("surname", 50)
}

object Students : IntIdTable() {
    val userId = reference("user_id", Users) // Внешний ключ на Users
    val group = varchar("group", 50) // Группа
}

object Teachers : IntIdTable() {
    val userId = reference("user_id", Users) // Внешний ключ на Users
}

object Subjects : IntIdTable() {
    val specialName = varchar("special_name", 50).uniqueIndex() // Специальное имя
    val title = varchar("title", 100) // Название
}

object Groups : Table() {
    private val specialName = varchar("special_name", 50) // Специальное название
    val title = varchar("title", 100) // Название группы
    val topics = text("topics") // Темы
    val studyTime = integer("study_time") // Время изучения

    override val primaryKey = PrimaryKey(specialName)
}

object Lessons : IntIdTable() {
    val date = date("date") // Дата
    val subjectId = reference("subject_id", Subjects) // Внешний ключ на Subjects
    val groupId = reference("group_id", Groups) // Внешний ключ на Groups
    val orderInDay = integer("order_in_day") // Порядковый номер в день
}

object Marks : Table() {
    val studentId = reference("student_id", Students) // Внешний ключ на Students
    val lessonId = reference("lesson_id", Lessons) // Внешний ключ на Lessons
    val grade = integer("grade") // Оценка
    override val primaryKey = PrimaryKey(studentId, lessonId) // Композитный первичный ключ
}