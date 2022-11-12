package com.codewithabdul.blog.services.serviceimpl;

import java.util.Date;

 
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;

import com.codewithabdul.blog.entity.Category;
import com.codewithabdul.blog.entity.Post;
import com.codewithabdul.blog.entity.User;
import com.codewithabdul.blog.exceptions.ResourceNotFoundException;
import com.codewithabdul.blog.payloads.PostDto;
import com.codewithabdul.blog.payloads.PostResponse;
import com.codewithabdul.blog.repositories.CategoryRepo;
import com.codewithabdul.blog.repositories.PostRepo;
import com.codewithabdul.blog.repositories.UserRepo;
import com.codewithabdul.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
	private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
	 
    @Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) 
	{
	    User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
	    Category  category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "Id",categoryId ));
	      
        Post post= this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        
        Post newpost=this.postRepo.save(post);
         

		return this.modelMapper.map(newpost, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) 
	{
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","category id", categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) 
	{
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public PostResponse  getAllPost(Integer pageNumber ,Integer pageSize,String sortBy)
	{

         Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
         Page<Post> pagePost=this.postRepo.findAll(p);
         List<Post> allPosts=pagePost.getContent();
		
		List<PostDto> postDtos=allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		  
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) 
	{
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> searchPost(String keywords) 
	{
		List<Post> posts=this.postRepo.findByTitleContaining(keywords);
		List<PostDto> postDto=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public void deletePost(Integer postId) 
	{
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) 
	{
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	

	

}
