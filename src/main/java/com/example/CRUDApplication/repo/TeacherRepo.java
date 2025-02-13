package com.example.CRUDApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CRUDApplication.model.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

    // @Query("select S.name as name, S.rollNo as rollNo from Teacher S")
    // public List<ProjectionInt> getPerticulatFunction();

    // public Teacher findByName(String name);

}
