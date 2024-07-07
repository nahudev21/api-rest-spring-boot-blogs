package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;

import java.util.List;

public interface IPostService {

    public PostEntityDTO createPost(PostEntityDTO postEntityDTO);

    public List<PostEntityDTO> getAllPost();

}
