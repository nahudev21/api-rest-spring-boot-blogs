package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;
import com.Nahudev.application_blog_api_rest.dto.PostResponse;


public interface IPostService {

    public PostEntityDTO createPost(PostEntityDTO postEntityDTO);

    public PostResponse getAllPost(int numPage, int pageSize, String orderBy, String sortDir);

    public PostEntityDTO getPostById(Long id);

    public PostEntityDTO editPost(PostEntityDTO postEntityDTO, Long id);

    public void deletePost(Long id);

}
