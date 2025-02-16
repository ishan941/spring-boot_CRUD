package com.example.CRUDApplication.model;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.CRUDApplication.repo.RoleRepo;

import java.util.*;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepo roleRepo;

    public RoleSeeder(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // this.loadRoles();
    }

    @Transactional
    private void loadRoles() {
        // RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.DRIVER };
        // Map<RoleEnum, String> roleDescriptionMap = Map.of(
        //         RoleEnum.USER, "Default user role",
        //         RoleEnum.ADMIN, "Administrator role",
        //         RoleEnum.DRIVER, "Super Administrator role");

        // Arrays.stream(roleNames).forEach((roleName) -> {
        //     Optional<Role> optionalRole = Optional.ofNullable(roleRepo.findByRoleEnum(roleName));

        //     optionalRole.ifPresentOrElse(System.out::println, () -> {
        //         Role roleToCreate = new Role();

        //         roleToCreate.setRoleEnum(roleName);
        //         roleToCreate.setDescription(roleDescriptionMap.get(roleName));

        //         roleRepo.save(roleToCreate);

            // });
        // });
    }
}
