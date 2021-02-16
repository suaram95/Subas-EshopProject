package com.example.subaseshopproject.dto;

import com.example.subaseshopproject.model.Category;
import com.example.subaseshopproject.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogRequestDto {

    private long id;
    private String name;
    private String text;
    private Date createdDate=new Date();
    private Category category;
    private Comment comment;
}
