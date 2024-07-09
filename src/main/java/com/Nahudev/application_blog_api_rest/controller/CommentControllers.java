package com.Nahudev.application_blog_api_rest.controller;

import com.Nahudev.application_blog_api_rest.dto.CommentDTO;
import com.Nahudev.application_blog_api_rest.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentControllers {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/posts/{id_post}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(name = "id_post") Long id_post,
                                                    @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(commentService.createComment(id_post, commentDTO), HttpStatus.CREATED);

    }

}
