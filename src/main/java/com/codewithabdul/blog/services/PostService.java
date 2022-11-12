package com.codewithabdul.blog.services;

import java.util.List;

import com.codewithabdul.blog.payloads.PostDto;
import com.codewithabdul.blog.payloads.PostResponse;

public interface PostService 
{
     // create post
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	// get post by categories
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	// get post by userId
	
	List<PostDto> getPostByUser(Integer userId);
	
	// get all post
	PostResponse getAllPost(Integer pageNumber ,Integer pageSize,String sortBy);
	
	// get post by id
	
	PostDto getPostById(Integer postId);
	
	// search post
	List<PostDto> searchPost(String keywords);
	
	void deletePost(Integer postId);
	
	PostDto updatePost(PostDto postDto,  Integer postId);

}
