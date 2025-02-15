package com.example.CRUDApplication.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CRUDApplication.Dto.LoginUserDto;
import com.example.CRUDApplication.Dto.RegisterUserDto;
import com.example.CRUDApplication.model.Role;
import com.example.CRUDApplication.model.RoleEnum;
import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.repo.RoleRepo;
import com.example.CRUDApplication.repo.StudentRepo;

@Service
public class AuthenticationService {
    private final StudentRepo studentRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepo roleRepo;
    private final Logger logger;

    public AuthenticationService(
            StudentRepo studentRepo,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepo roleRepo) {
        this.authenticationManager = authenticationManager;
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.logger=LoggerFactory.getLogger(AuthenticationService.class);
    }

    public Student signup(RegisterUserDto input) {
        try {
            Role role = roleRepo.findByRoleEnum(RoleEnum.valueOf(input.getRole()));
            logger.debug("Role found: {}", role);
            if (role == null) {
                Role role2 = new Role();
                role2.setRoleEnum(RoleEnum.valueOf(input.getRole()));
                roleRepo.save(role2);
                role = role2;
                logger.debug("New role created: {}", role);
            }
            Student student = new Student();
            student.setRole(role);
            student.setUsername(input.getUsername())
                   .setEmail(input.getEmail())
                   .setPassword(passwordEncoder.encode(input.getPassword())).setRolename(input.getRole());
            Student savedStudent = studentRepo.save(student);
            logger.debug("Student saved with role: {}", savedStudent.getRole());
            return savedStudent;
        } catch (Exception ex) {
            logger.error("Error during signup: ", ex);
            throw new RuntimeException("User not found" + ex.getMessage());
        }

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