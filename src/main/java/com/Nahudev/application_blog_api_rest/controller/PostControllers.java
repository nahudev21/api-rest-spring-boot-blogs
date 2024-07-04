package com.Nahudev.application_blog_api_rest.controller;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;
import com.Nahudev.application_blog_api_rest.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostControllers {

    @Autowired
    private IPostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostEntityDTO> createPost(@RequestBody PostEntityDTO postEntityDTO) {

        return new ResponseEntity<>(postService.createPost(postEntityDTO), HttpStatus.CREATED);

    }

}
