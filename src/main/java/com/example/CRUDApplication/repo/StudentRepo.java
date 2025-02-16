package com.example.CRUDApplication.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.projection.ProjectionInt;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    //
    // Jpql(java persestend qury language)
    @Query("select S.username as name, S.roll as roll from Student S")
    public List<ProjectionInt> getPerticulatFunction();

    // Custom method finder
    public Optional<Student> findByUsername(String username);

    public Optional<Student> findByEmail(String email);

}
