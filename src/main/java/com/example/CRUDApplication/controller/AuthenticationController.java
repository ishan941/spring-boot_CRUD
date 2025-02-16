package com.example.CRUDApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.Dto.LoginUserDto;
import com.example.CRUDApplication.Dto.RegisterUserDto;
import com.example.CRUDApplication.apiResponse.ApiResponse;
import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.repo.StudentRepo;
import com.example.CRUDApplication.service.AuthenticationService;
import com.example.CRUDApplication.service.JwtService;
import com.example.CRUDApplication.service.StudentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AuthenticationController {

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationService authenticationService;

    private final StudentService studentService;

    public AuthenticationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/registeruser")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody RegisterUserDto registerUserDto) {
        authenticationService.signup(registerUserDto);
        ApiResponse apiResponse = ApiResponse.builder().messsage("Success").statusCode(HttpStatus.OK.value()).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/loginuser")
    public ResponseEntity<ApiResponse> postlogin(@RequestBody LoginUserDto loggLoginUserDto) {
        Student student = authenticationService.authenticate(loggLoginUserDto);
        String role=student.getRole().getRoleEnum().toString();
        String jwtToken = jwtService.generateToken(student);    
        ApiResponse apiResponse = ApiResponse.builder().messsage("Success").statusCode(HttpStatus.OK.value()).token(jwtToken).role(role).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

  
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    @GetMapping("/path")
    public ResponseEntity<ApiResponse> getMethodName() {
        List<Student> list = studentService.getStudent();

        ApiResponse response = ApiResponse.<Student>builder().messsage("suceeefully fetched user")
                .statusCode(HttpStatus.OK.value()).listData(list).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
