package com.example.CRUDApplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.apiResponse.ApiResponse;
import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.projection.ProjectionDto;
import com.example.CRUDApplication.repo.StudentRepo;
import com.example.CRUDApplication.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Validated
@RequestMapping("/api")

public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    private final Logger logger;
    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.logger = LoggerFactory.getLogger(StudentController.class);
        this.studentService = studentService;

    }

    @PostMapping("/saveStudent")

    public ResponseEntity<ApiResponse> postMethodName(@Valid @RequestBody Student student) {
        try {
            Student student3 = studentRepo.findByUsername(student.getUsername());
            if (student3 == null) {
                studentService.save(student);

                ApiResponse apiResponse = ApiResponse.builder().messsage("Success").statusCode(HttpStatus.OK.value())
                        .build();
                return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

            } else {
                ApiResponse apiResponse = ApiResponse.builder().messsage("User already exist")
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);

            }
        } catch (Exception exception) {
            ApiResponse apiResponse = ApiResponse.builder().messsage("Error")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);

        }

    }

    @GetMapping("/getAllStduent")
    public ResponseEntity<ApiResponse> getMethodName() {
        List<Student> sutudentList = studentService.getStudent();
        ApiResponse apiResponse = ApiResponse.<Student>builder().messsage("success").statusCode(HttpStatus.OK.value())
                .listData(sutudentList).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<ApiResponse> deleteStudentById(@PathVariable("id") int id) {
        studentService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder().messsage("Successfully deleted")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping("/updateStudent/{id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        studentService.update(id, student);
        ApiResponse apiResponse = ApiResponse.builder().messsage("Successfully updated")
                .statusCode(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/getLimited")
    public ResponseEntity<ApiResponse> getLimited() {
        List<ProjectionDto> list = studentService.getPerticularField();
        for (ProjectionDto lProjectionDto : list) {
            logger.info("UserData", lProjectionDto.getUsername(), lProjectionDto.getRoll());
        }

        ApiResponse apiResponse = ApiResponse.<ProjectionDto>builder().messsage("Successfully fecth")
                .statusCode(HttpStatus.OK.value()).listData(list).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
