package com.codewithabdul.blog.repositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithabdul.blog.entity.Category;
import com.codewithabdul.blog.entity.Post;
import com.codewithabdul.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>
{
     List<Post> findByCategory(Category category);

	 List<Post> findByUser(User user);

	List<Post> findByTitleContaining(String Title);
}
