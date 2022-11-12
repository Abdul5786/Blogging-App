package com.codewithabdul.blog.controllers;

import java.util.List;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codewithabdul.blog.payloads.ApiResponse;
import com.codewithabdul.blog.payloads.CategoryDto;
import com.codewithabdul.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController 
{
	@Autowired
    private CategoryService categoryService;
    
	@PostMapping("/")
	public ResponseEntity<CategoryDto> create( @Valid @RequestBody CategoryDto categoryDto)
	{
		 CategoryDto createCategory =this.categoryService.createCategory(categoryDto);
		 return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> upDate( @Valid @RequestBody CategoryDto categoryDto,@PathVariable ("catId") Integer cId)
	{
		 CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto, cId);
		 return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
    @DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable  ("catId") Integer cId)
	{
		this.categoryService.deleteCategory(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("categories is deleted succesfully !!",true),HttpStatus.OK);
	}
     @GetMapping("/{catId}")
     public ResponseEntity<CategoryDto> getSingleId(@PathVariable  ("catId") Integer cId)
     {
    	     CategoryDto categoryDto=this.categoryService.getCategory(cId);
    	     return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
     }

     @GetMapping("/")
     public ResponseEntity<List<CategoryDto>> getAll()
     {
    	 List<CategoryDto> categories=this.categoryService.getCategories();
    	 return ResponseEntity.ok(categories);
     }




}
