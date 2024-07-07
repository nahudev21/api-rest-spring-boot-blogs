package com.Nahudev.application_blog_api_rest.controller;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;
import com.Nahudev.application_blog_api_rest.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostControllers {

    @Autowired
    private IPostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostEntityDTO> createPost(@RequestBody PostEntityDTO postEntityDTO) {
        return new ResponseEntity<>(postService.createPost(postEntityDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<?> getAllPosts() {
        List<PostEntityDTO> postList = postService.getAllPost();

        if (postList != null) {
            return ResponseEntity.ok(postList);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getById/{post_id}")
    @ResponseBody
    public ResponseEntity<PostEntityDTO> getPostById(@PathVariable(name = "post_id") Long post_id) {
        return ResponseEntity.ok(postService.getPostById(post_id));
    }

}
