package com.example.CRUDApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.Dto.LoginUserDto;
import com.example.CRUDApplication.Dto.RegisterUserDto;
import com.example.CRUDApplication.apiResponse.ApiResponse;
import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.repo.StudentRepo;
import com.example.CRUDApplication.service.AuthenticationService;
import com.example.CRUDApplication.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthenticationController {

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/registeruser")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody RegisterUserDto registerUserDto) {
        authenticationService.signup(registerUserDto);
        ApiResponse apiResponse = ApiResponse.builder().messsage("Success").statusCode(HttpStatus.OK.value()).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/loginuser")
    public ResponseEntity<ApiResponse> postlogin(@RequestBody LoginUserDto loggLoginUserDto) {
        Student student = authenticationService.authenticate(loggLoginUserDto);
        String jwtToken = jwtService.generateToken(student);
        ApiResponse apiResponse = ApiResponse.builder().messsage("Success").statusCode(HttpStatus.OK.value())
                .token(jwtToken).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
