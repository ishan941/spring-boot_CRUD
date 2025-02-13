package com.example.CRUDApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.projection.ProjectionDto;
import com.example.CRUDApplication.projection.ProjectionInt;
import com.example.CRUDApplication.repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Override
    public void save(Student student) {
        studentRepo.save(student);

    }

    @Override
    public List<Student> getStudent() {
        List<Student> getAll = studentRepo.findAll();
        return getAll.isEmpty() ? new ArrayList<>() : getAll;
    }

    @Override
    public void delete(int id) {
        studentRepo.deleteById(id);
    }

    @Override
    public void update(int id, Student student) {
        Optional<Student> oStudent = studentRepo.findById(id);
        if (oStudent.isPresent()) {
            Student preseStudent = oStudent.get();
            if (student.getUsername() != null) {
                preseStudent.setUsername(student.getUsername());
            }
            if (student.getStudentgrade() != 0) {
                preseStudent.setStudentgrade(student.getStudentgrade());
            }
            if (student.getRoll() != 0) {
                preseStudent.setRoll(student.getRoll());
            }
            studentRepo.save(preseStudent);

        }
    }

    //
    // Get Perticular
    @Override
    public List<ProjectionDto> getPerticularField() {

        try {
            List<ProjectionInt> projectionList = studentRepo.getPerticulatFunction();
            for (ProjectionInt abInt : projectionList) {
                abInt.getName();
                abInt.getRoll();
            }
            List<ProjectionDto> projectionDtos = new ArrayList<>();
            for (ProjectionInt abInt : projectionList) {
                projectionDtos.add(new ProjectionDto(abInt.getName(), abInt.getRoll()));
            }
            return projectionDtos;
        } catch (Exception ex) {
            throw new RuntimeException("Error: " + ex.getMessage());
        }

    }

}
