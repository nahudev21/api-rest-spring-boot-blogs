package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;
import com.Nahudev.application_blog_api_rest.model.PostEntity;
import com.Nahudev.application_blog_api_rest.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService{

    @Autowired
    private IPostRepository postRepository;

    @Override
    public PostEntityDTO createPost(PostEntityDTO postEntityDTO) {
        PostEntity postEntity = mapOutPostEntity(postEntityDTO);

        PostEntity newPost = postRepository.save(postEntity);

        return mapOutPostDTO(newPost);
    }

    @Override
    public List<PostEntityDTO> getAllPost() {
        List<PostEntity> postEntities = postRepository.findAll();

        return postEntities.stream().map(this::mapOutPostDTO).collect(Collectors.toList());
    }

    private PostEntityDTO mapOutPostDTO(PostEntity postEntity) {
        PostEntityDTO postDTO = new PostEntityDTO();

        postDTO.setPost_id(postEntity.getPost_id());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setDescription(postEntity.getDescription());
        postDTO.setContent(postEntity.getContent());

        return postDTO;
    }

    private PostEntity mapOutPostEntity(PostEntityDTO postEntityDTO) {
        PostEntity postEntity = new PostEntity();

        postEntity.setTitle(postEntityDTO.getTitle());
        postEntity.setDescription(postEntityDTO.getDescription());
        postEntity.setContent(postEntityDTO.getContent());

        return postEntity;
    }

}
