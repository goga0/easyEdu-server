package com.r4men.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EduPlace(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<EduPlace>(EduPlaces)
    var name by EduPlaces.name
}

class User(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<User>(Users)
    var eduPlaceId by Users.eduPlaceId
    var login by Users.login
    var name by Users.name
    var surname by Users.surname
    var password by Users.password
    var role by Users.role
}

class Student(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Student>(Students)
    var studentId by Students.userId
    var groupId by Students.groupId
    var name by Students.name
    var surname by Students.surname
}

class Teacher(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Teacher>(Teachers)
    var teacherId by Teachers.userId
    var name by Teachers.name
    var surname by Teachers.surname
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
    var topics by Subjects.topics
    var studyTime by Subjects.studyTime
}

class SubjectTopic(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<SubjectTopic>(SubjectTopics)
    var subjectId by SubjectTopics.subjectId
    var title by SubjectTopics.title
    var timeForLearn by SubjectTopics.timeForLearn
}

class Lesson(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<Lesson>(Lessons)
    var
}

