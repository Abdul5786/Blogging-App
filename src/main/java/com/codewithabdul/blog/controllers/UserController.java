package com.codewithabdul.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithabdul.blog.payloads.ApiResponse;
import com.codewithabdul.blog.payloads.UserDto;
import com.codewithabdul.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
     
     //creating user
     @PostMapping("/")
     public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto)
     {
    	 UserDto createUserDto=this.userService.createUser(userDto);
    	 return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
     }
     //updating user
     @PutMapping("/{userId}")
    public ResponseEntity<UserDto> quest(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid)
    {
      UserDto updatedUser=	this.userService.updateUser(userDto, uid);
      return ResponseEntity.ok(updatedUser);
    	
    }
     // deleting user
      @PreAuthorize("hasRole('ADMIN')")
     @DeleteMapping("/{userId}")
     public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid)
     {
    	 this.userService.deleteUser(uid);
    	 return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted succesfuuly",true),HttpStatus.OK);
     }
     // getting all users 
      @GetMapping
     public ResponseEntity<List<UserDto>> getAllUser()
     {
    	 return ResponseEntity.ok(this.userService.getAllUsers());
     }
       // getting only single user
      @GetMapping("{userId}")
      public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
      {
     	 return ResponseEntity.ok(this.userService.getUserById(userId));
     	 
      }
      













}
