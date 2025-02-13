package com.example.CRUDApplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUDApplication.model.Teacher;
import com.example.CRUDApplication.repo.TeacherRepo;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    @Override
    public List<Teacher> getTeachers() {
        List<Teacher> getAllTeachers = teacherRepo.findAll();
        return getAllTeachers.isEmpty() ? new ArrayList<>() : getAllTeachers;

    }

}
