package com.menumaster.menumaster.authentication.domain.dto;

import com.menumaster.menumaster.authentication.enums.RoleName;

public record UpdateUserDTO(
        String email,

        String password,

        String name,

        RoleName role
) {
}
