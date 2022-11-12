package com.codewithabdul.blog.services;

import com.codewithabdul.blog.payloads.CommentDto;

public interface CommentService 
{
   CommentDto createComment(CommentDto commentDto,Integer postId);
   void deleteComment(Integer commentId);
}
