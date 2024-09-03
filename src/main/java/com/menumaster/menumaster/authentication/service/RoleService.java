package com.menumaster.menumaster.authentication.service;

import com.menumaster.menumaster.authentication.domain.entity.Role;
import com.menumaster.menumaster.authentication.enums.RoleName;
import com.menumaster.menumaster.authentication.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> findRoleByName(RoleName role) {
        return roleRepository.findByName(role);
    }
}
