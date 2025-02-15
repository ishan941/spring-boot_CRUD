package com.example.CRUDApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CRUDApplication.model.Role;
import com.example.CRUDApplication.model.RoleEnum;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Transactional(readOnly = true)

    public Role findByRoleEnum(RoleEnum roleEnum);
    Hibernate.initialize(role.getUserlist()); 

}
