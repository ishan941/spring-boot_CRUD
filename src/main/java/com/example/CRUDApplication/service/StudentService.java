package com.example.CRUDApplication.service;

import java.util.List;

import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.projection.ProjectionDto;

public interface StudentService {
    public void save(
            Student student);

    public List<Student> getStudent();

    public void delete(int id);

    public void update(int id, Student student);

    public List<ProjectionDto> getPerticularField();
}
