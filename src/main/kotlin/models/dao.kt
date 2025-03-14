package com.r4men.models

import com.r4men.models.Lesson.Companion.backReferencedOn
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EduPlace(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<EduPlace>(EduPlaces)
    var name by EduPlaces.name
}

class User(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<User>(Users)
    var eduPlace by EduPlace referencedOn Users.eduPlaceId
    var login by Users.login
    var name by Users.name
    var surname by Users.surname
    var password by Users.password
    var role by Users.role
}

class Student(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Student>(Students)
    var studentId by User referencedOn Students.userId
    var eduPlace by EduPlace referencedOn  Students.eduPlaceId
    var groupId by Group referencedOn Students.groupId
}

class Teacher(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Teacher>(Teachers)
    var eduPlace by EduPlace referencedOn  Teachers.eduPlaceId
    var teacherId by User referencedOn  Teachers.userId
}

class Group(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Group>(Groups)
    var groupName by Groups.specialName
    var groupTitle by Groups.title
}

class Subject(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Subject>(Subjects)
    var name by Subjects.specialName
    var title by Subjects.title
    var group by Group referencedOn  Subjects.groups
    var teacher by Teacher referencedOn  Subjects.teacher
    var topics by SubjectTopic referencedOn  Subjects.topics
    var studyTime by Subjects.studyTime
}

class SubjectTopic(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<SubjectTopic>(SubjectTopics)
    var subjectId by Subject referencedOn  SubjectTopics.subjectId
    var title by SubjectTopics.title
    var timeForLearn by SubjectTopics.timeForLearn
}

class Lesson(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Lesson>(Lessons)
    var date by Lessons.date
    var subjectId by Subject referencedOn  Lessons.subjectId
    var topic by SubjectTopic referencedOn  Lessons.topic
    var groupId by Group referencedOn  Lessons.groupId
}

