package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.CommentDTO;

import java.util.List;

public interface ICommentService {

    public CommentDTO createComment(Long id_post, CommentDTO commentDTO);

    public List<CommentDTO> getAllCommentsByPost(Long id_post);

    public CommentDTO getCommentByPost(Long id_post, Long id_comment);

}
