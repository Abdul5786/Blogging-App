package com.codewithabdul.blog.payloads;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto 
{
   private Integer categoryId;
   @NotEmpty
   @Size(min=4,message="min size of title should be 4")
   private String categoryTitle;
   @NotEmpty
   @Size(min=20,message="min description of title should be 10")
   private String categoryDescription;
}
