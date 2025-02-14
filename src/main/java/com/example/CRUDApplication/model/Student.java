package com.example.CRUDApplication.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor //
@Data // Getter setter
@Entity // Creates orm
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be blank")
    @Column(nullable = false)
    private String username;
    private int roll;
    private float studentgrade;
    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be blank")
    @Column(nullable = false)
    private String password;

    @Email(message = "Valid email required")
    private String email;

    private String rolename;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    @JsonBackReference("userrole")
    private Role role;

    public Student setUsername(String username) {
        this.username = username;
        return this;
    }

    public Student setRoll(int roll) {
        this.roll = roll;
        return this;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public Student setRolename(String rolename) {
        this.rolename = rolename;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + rolename);
        return List.of(authority);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
