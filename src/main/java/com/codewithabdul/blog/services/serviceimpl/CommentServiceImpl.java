package com.codewithabdul.blog.services.serviceimpl;

import org.modelmapper.ModelMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithabdul.blog.entity.Comment;
import com.codewithabdul.blog.entity.Post;
import com.codewithabdul.blog.exceptions.ResourceNotFoundException;
import com.codewithabdul.blog.payloads.CommentDto;
import com.codewithabdul.blog.repositories.CommentRepo;
import com.codewithabdul.blog.repositories.PostRepo;
import com.codewithabdul.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	 @Autowired
	 private PostRepo postRepo;
	 @Autowired
	 private CommentRepo commentRepo;
	 @Autowired
	 private ModelMapper modelMapper;
	
	public CommentDto createComment(CommentDto commentDto, Integer postId) 
	{
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id", postId));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment =this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) 
	{
		    Comment comment= this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id", commentId));
		     this.commentRepo.delete(comment);

	}

}
