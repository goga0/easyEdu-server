package com.r4men.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object EduPlaces: IntIdTable(){
    val name = varchar("name", 255)
}

object Users : IntIdTable() {
    val eduPlaceId = reference("eduPlaceID", EduPlaces.id)
    val login = varchar("login", 50).uniqueIndex() // Логин как первичный ключ
    val name = varchar("name", 50)
    val surname = varchar("surname", 50)
    val password = varchar("password", 50)
    val role = varchar("role", 10)
}

object Students : IntIdTable() {
    val userId = reference("user_id ", Users.id) // Внешний ключ на Users
    val groupId = reference("group", Groups.id) // Группа
    val name = reference("studentName", Users.name)
    val surname = reference("studentSurname", Users.surname)
}

object Teachers : IntIdTable() {
    val userId = reference("user_id", Users.id) // Внешний ключ на Users
    val name = reference("teacherName", Users.name)
    val surname = reference("teacherSurname", Users.surname)
}

object Groups : IntIdTable() {
    val specialName = varchar("special_name", 50).uniqueIndex() // Специальное название
    val title = varchar("title", 100) // Название группы
}

object Subjects : IntIdTable() {
    val specialName = varchar("special_name", 50).uniqueIndex() // Специальное имя
    val title = varchar("title", 100) // Название
    val topics = varchar("topics", 255).references(SubjectTopics.title) // Темы
    val studyTime = integer("study_time") // Время изучения
}
object SubjectTopics: IntIdTable(){
    val subjectId = reference("subjectId", Subjects.id)
    val title = varchar("topic", 255)
    val timeForLearn = integer("timeForLearn")
}

object Lessons : IntIdTable() {
    val date = varchar("date", 10) // Дата
    val subjectId = reference("subject_id", Subjects.id) // Внешний ключ на Subjects
    val topic = reference("LessonTopic", SubjectTopics.title)
    val groupId = reference("group_id", Groups) // Внешний ключ на Groups
    val orderInDay = integer("order_in_day") // Порядковый номер в день
}

object Marks : Table() {
    private val studentId = reference("student_id", Students.userId) // Внешний ключ на Students
    private val lessonId = reference("lesson_id", Lessons.id) // Внешний ключ на Lessons
    val grade = integer("grade") // Оценка
    override val primaryKey = PrimaryKey(studentId, lessonId) // Композитный первичный ключ
}

object ActiveTokens: IntIdTable(){
    private val userId = reference("userId", Users.id)
    private val value = varchar("token", 255)
}