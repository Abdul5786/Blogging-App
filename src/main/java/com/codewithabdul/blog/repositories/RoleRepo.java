package com.codewithabdul.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithabdul.blog.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>
{

}
