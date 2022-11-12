package com.codewithabdul.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codewithabdul.blog.payloads.ApiResponse;
import com.codewithabdul.blog.payloads.CommentDto;
import com.codewithabdul.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController 
{
	@Autowired
	private CommentService commentService;
	
	
	
	@PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId)
	{
		CommentDto createcomment=this.commentService.createComment(comment, postId);
	    return new ResponseEntity<CommentDto>(createcomment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> createComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted succesfully",true),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
