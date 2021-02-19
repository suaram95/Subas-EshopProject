package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.dto.CommentRequestDto;
import com.example.subaseshopproject.model.Blog;
import com.example.subaseshopproject.model.Comment;
import com.example.subaseshopproject.model.User;
import com.example.subaseshopproject.service.BlogService;
import com.example.subaseshopproject.service.CommentService;
import com.example.subaseshopproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final BlogService blogService;

    @PostMapping("/comment/add")
    public String addComment(@ModelAttribute("comment") CommentRequestDto commentRequestDto,
                             @RequestParam("userId") long userId,
                             @RequestParam("blogId") long blogId) {
        Optional<User> userById = userService.findUserById(userId);
        if (userById.isPresent()){
            User user = userById.get();
            Comment comment=Comment.builder()
                    .commentText(commentRequestDto.getCommentText())
                    .createdDate(commentRequestDto.getCreatedDate())
                    .user(user)
                    .build();
            commentService.save(comment);
            Optional<Blog> blogById = blogService.findById(blogId);
            if (blogById.isPresent()){
                Blog blog = blogById.get();
                blog.setComment(comment);
            }
            return "redirect:/singleBlog?id="+blogId;
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/comment/delete")
    public String removeComment(@RequestParam("id") long id){
        commentService.delete(id);
        return "redirect:/";
    }
}
