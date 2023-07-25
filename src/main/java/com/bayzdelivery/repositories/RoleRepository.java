package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
}
