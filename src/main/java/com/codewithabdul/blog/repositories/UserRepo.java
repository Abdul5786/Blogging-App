package com.codewithabdul.blog.repositories;







import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithabdul.blog.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>
{
	
	

	Optional<User> findByEmail(String email);
	
}
