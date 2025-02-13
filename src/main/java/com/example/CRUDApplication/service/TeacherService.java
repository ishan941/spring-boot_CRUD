package com.example.CRUDApplication.service;

import java.util.List;

import com.example.CRUDApplication.model.Teacher;

public interface TeacherService {
    public void saveTeacher(Teacher teacher);

    public List<Teacher> getTeachers();

}
