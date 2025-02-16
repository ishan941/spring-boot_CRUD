package com.example.CRUDApplication.service;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.CRUDApplication.model.Student;
import com.example.CRUDApplication.repo.StudentRepo;

@Configuration
public class ApplicationConfiguration {
    private final StudentRepo studentRepo;

    public ApplicationConfiguration(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Bean
UserDetailsService userDetailsService() {
    return username -> {
        Optional<Student> studentOptional = studentRepo.findByEmail(username);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return new org.springframework.security.core.userdetails.User(
                student.getEmail(),
                student.getPassword(),
                student.getAuthorities() // Ensure this returns the correct roles
            );
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    };
}    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}