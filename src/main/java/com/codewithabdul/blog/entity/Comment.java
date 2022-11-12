package com.codewithabdul.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer commentId;
    
    private String content;
    @ManyToOne
    private Post post;
}
