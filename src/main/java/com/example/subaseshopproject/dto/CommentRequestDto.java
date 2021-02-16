package com.example.subaseshopproject.dto;

import com.example.subaseshopproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {

    private long id;
    private String commentText;
    private Date createdDate=new Date();
    private User user;
}
