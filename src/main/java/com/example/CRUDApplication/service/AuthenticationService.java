package com.example.CRUDApplication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CRUDApplication.Dto.LoginUserDto;
import com.example.CRUDApplication.Dto.RegisterUserDto;
import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.repo.StudentRepo;

@Service
public class AuthenticationService {
    private final StudentRepo studentRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            StudentRepo studentRepo,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Student signup(RegisterUserDto input) {
        Student student = new Student()
                .setUsername(input.getUsername())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return studentRepo.save(student);
    }

    public Student authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()));

        return studentRepo.findByEmail(input.getEmail())
                .orElseThrow();
    }
}