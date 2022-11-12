package com.codewithabdul.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import com.codewithabdul.blog.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto 
{
    private int id;
    @NotEmpty
    @Size(min=4,message="username must be in minimum 4 characters !!")
    private String name;
    
    @NotEmpty
    @Email(message="email id is not valid !!")
    private String email;
   
    @NotEmpty
    @Size(min=4,max=10,message="password must be min of 3 chars")
    private String password;
   
    @NotEmpty
    private String about;
    
    private Set<RoleDto> roles=new HashSet<>();
	
    
}
