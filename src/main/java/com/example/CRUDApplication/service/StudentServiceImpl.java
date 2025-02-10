package com.example.CRUDApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUDApplication.model.Student;
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
            if (student.getName() != null) {
                preseStudent.setName(student.getName());
            }
            if (student.getGrade() != 0) {
                preseStudent.setGrade(student.getGrade());
            }
            if (student.getRollNo() != 0) {
                preseStudent.setRollNo(student.getRollNo());
            }
            studentRepo.save(preseStudent);

        }
    }

}
