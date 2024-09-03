package com.menumaster.menumaster.authentication.domain.dto;

import com.menumaster.menumaster.authentication.domain.entity.Role;

import java.util.List;

public record RecoveryUserDto(

        Long id,
        String email,
        List<Role> roles

) {
}
