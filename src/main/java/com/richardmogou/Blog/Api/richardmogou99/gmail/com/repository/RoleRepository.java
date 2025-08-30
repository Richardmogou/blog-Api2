package com.richardmogou.Blog.Api.richardmogou99.gmail.com.repository;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);

}
