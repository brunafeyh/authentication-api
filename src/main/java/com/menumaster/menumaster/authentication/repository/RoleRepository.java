package com.menumaster.menumaster.authentication.repository;

import com.menumaster.menumaster.authentication.domain.entity.Role;
import com.menumaster.menumaster.authentication.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName role);

}
