package com.Nahudev.application_blog_api_rest.controller;

import com.Nahudev.application_blog_api_rest.dto.CommentDTO;
import com.Nahudev.application_blog_api_rest.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/posts/{id_post}/comments")
    @ResponseBody
    public List<CommentDTO> getAllCommentsByPost(@PathVariable(name = "id_post") Long id_post) {
        return commentService.getAllCommentsByPost(id_post);
    }

    @GetMapping("/posts/{id_post}/comments/{id}")
    @ResponseBody
    public ResponseEntity<CommentDTO> getCommentByPost(@PathVariable(name = "id_post") Long id_post,
                                                       @PathVariable(name = "id") Long id_comment) {

        CommentDTO commentDTO = commentService.getCommentByPost(id_post, id_comment);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/posts/{id_post}/comments/{id}")
    public ResponseEntity<CommentDTO> editComment(@PathVariable(name = "id_post") Long id_post,
                                                  @PathVariable(name = "id") Long id_comment,
                                                  @RequestBody CommentDTO commentDTO) {

        CommentDTO commentResponse = commentService.editComment(id_post, id_comment, commentDTO);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);

    }

    @DeleteMapping("/posts/{id_post}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "id_post") Long id_post,
                                                @PathVariable(name = "id") Long id_comment) {

        commentService.deleteComment(id_post, id_comment);
        return new ResponseEntity<>("Comentario eliminado exitosamente!", HttpStatus.OK);

    }

}
