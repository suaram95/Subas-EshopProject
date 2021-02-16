package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.Comment;
import com.example.subaseshopproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public void delete(long id){
        commentRepository.deleteById(id);
    }


}
