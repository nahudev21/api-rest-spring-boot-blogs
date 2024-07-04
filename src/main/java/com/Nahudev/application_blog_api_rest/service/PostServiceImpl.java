package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;
import com.Nahudev.application_blog_api_rest.model.PostEntity;
import com.Nahudev.application_blog_api_rest.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService{

    @Autowired
    private IPostRepository postRepository;

    @Override
    public PostEntityDTO createPost(PostEntityDTO postEntityDTO) {
        PostEntity postEntity = new PostEntity();

        postEntity.setTitle(postEntityDTO.getTitle());
        postEntity.setDescription(postEntityDTO.getDescription());
        postEntity.setContent(postEntityDTO.getContent());

        PostEntity newPost = postRepository.save(postEntity);

        PostEntityDTO postResponse = new PostEntityDTO();

        postResponse.setPost_id(newPost.getPost_id());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
