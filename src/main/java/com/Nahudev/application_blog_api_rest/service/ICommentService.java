package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.CommentDTO;

public interface ICommentService {

    public CommentDTO createComment(Long id_post, CommentDTO commentDTO);

}
