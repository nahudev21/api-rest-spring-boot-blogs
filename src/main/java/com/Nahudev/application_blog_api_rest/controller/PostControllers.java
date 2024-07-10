package com.Nahudev.application_blog_api_rest.controller;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;
import com.Nahudev.application_blog_api_rest.dto.PostResponse;
import com.Nahudev.application_blog_api_rest.service.IPostService;
import com.Nahudev.application_blog_api_rest.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
public class PostControllers {

    @Autowired
    private IPostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostEntityDTO> createPost(@RequestBody PostEntityDTO postEntityDTO) {
        return new ResponseEntity<>(postService.createPost(postEntityDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int numPage,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DEFAULT_ADDRESS, required = false) String sortDir) {

        PostResponse postList = postService.getAllPost(numPage, pageSize, orderBy, sortDir);

        if (postList != null) {
            return ResponseEntity.ok(postList);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public ResponseEntity<PostEntityDTO> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PostEntityDTO> editPost(@RequestBody PostEntityDTO postEntityDTO,
                                                  @PathVariable(name = "id") Long id) {

        PostEntityDTO postResponse = postService.editPost(postEntityDTO, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Posteo eliminado exitosamente!", HttpStatus.OK);
    }

}
