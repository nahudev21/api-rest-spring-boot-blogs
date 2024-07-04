package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;

public interface IPostService {

    public PostEntityDTO createPost(PostEntityDTO postEntityDTO);

}
