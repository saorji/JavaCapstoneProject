//package com.samuelhrm.utils;
//
//
//import com.samuelhrm.entities.ERole;
//import com.samuelhrm.entities.Role;
//import com.samuelhrm.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//@Component
//public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//
//    private boolean alreadySetup = false;
//
//
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(final ContextRefreshedEvent event) {
//        if (alreadySetup) {
//            return;
//        }
//
//        final Role adminRole = createRoleIfNotFound(ERole.ROLE_ADMIN);
//        final Role patientRole = createRoleIfNotFound(ERole.ROLE_PATIENT);
//        final Role doctorRole = createRoleIfNotFound(ERole.ROLE_DOCTOR);
//
//
//        alreadySetup = true;
//    }
//
//    @Transactional
//    Role createRoleIfNotFound(final ERole name) {
//        Role role = new Role();
//        role.setName(name);
//        Role savedRole = roleRepository.save(role);
//
//
//
//        return role;
//    }
//
//}