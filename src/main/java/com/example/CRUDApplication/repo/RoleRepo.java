package com.example.CRUDApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRUDApplication.model.Role;
import com.example.CRUDApplication.model.RoleEnum;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    public Role findByRoleEnum(RoleEnum roleEnum);

}
