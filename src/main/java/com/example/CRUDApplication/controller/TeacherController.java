package com.example.CRUDApplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.apiResponse.ApiResponse;
import com.example.CRUDApplication.model.Teacher;
import com.example.CRUDApplication.repo.TeacherRepo;
import com.example.CRUDApplication.service.TeacherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")

public class TeacherController {

    private final Logger logger;
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService, TeacherRepo teacherRepo) {
        this.logger = LoggerFactory.getLogger(TeacherController.class);
        this.teacherService = teacherService;

    }

    @PostMapping("/addTeacher")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody Teacher teacher) {
        try {
            teacherService.saveTeacher(teacher);
            ApiResponse apiResponse = ApiResponse.builder().statusCode(HttpStatus.OK.value()).messsage("Success")
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        } catch (Exception ex) {
            ApiResponse apiResponse = ApiResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())

                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }

    @GetMapping("/getTeachers")
    public ResponseEntity<ApiResponse> getMethodByName() {
        List<Teacher> teachrList = teacherService.getTeachers();

        ApiResponse apiResponse = ApiResponse.<Teacher>builder().messsage("success").statusCode(HttpStatus.OK.value())
                .listData(teachrList).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
}
