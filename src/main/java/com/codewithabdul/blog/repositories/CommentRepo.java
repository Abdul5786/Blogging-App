package com.codewithabdul.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithabdul.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>
{

}
