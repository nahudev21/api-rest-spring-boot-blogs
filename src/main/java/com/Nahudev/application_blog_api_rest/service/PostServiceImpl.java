package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.PostEntityDTO;
import com.Nahudev.application_blog_api_rest.dto.PostResponse;
import com.Nahudev.application_blog_api_rest.exceptions.ResourceNotFoundException;
import com.Nahudev.application_blog_api_rest.model.PostEntity;
import com.Nahudev.application_blog_api_rest.repository.IPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IPostRepository postRepository;

    @Override
    public PostEntityDTO createPost(PostEntityDTO postEntityDTO) {
        PostEntity postEntity = mapOutPostEntity(postEntityDTO);

        PostEntity newPost = postRepository.save(postEntity);

        return mapOutPostDTO(newPost);
    }

    @Override
    public PostResponse getAllPost(int numPage, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numPage, pageSize, sort);
        Page<PostEntity> posts = postRepository.findAll(pageable);

        List<PostEntity> postEntities = posts.getContent();
        List<PostEntityDTO> content = postEntities.stream().map(this::mapOutPostDTO).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalItems(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostEntityDTO getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Publicacion", "id", id));
        return mapOutPostDTO(postEntity);
    }

    @Override
    public PostEntityDTO editPost(PostEntityDTO postEntityDTO, Long id) {
        PostEntity postFound = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Publicacion", "id", id));

        postFound.setTitle(postEntityDTO.getTitle());
        postFound.setDescription(postEntityDTO.getDescription());
        postFound.setContent(postEntityDTO.getContent());
        PostEntity postEdited = postRepository.save(postFound);

        return mapOutPostDTO(postEdited);
    }

    @Override
    public void deletePost(Long id) {
        PostEntity postFound = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Publicacion", "id", id));

        postRepository.delete(postFound);
    }

    private PostEntityDTO mapOutPostDTO(PostEntity postEntity) {

        return modelMapper.map(postEntity, PostEntityDTO.class);
    }

    private PostEntity mapOutPostEntity(PostEntityDTO postEntityDTO) {

        return modelMapper.map(postEntityDTO, PostEntity.class);
    }

}
