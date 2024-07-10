package com.Nahudev.application_blog_api_rest.service;

import com.Nahudev.application_blog_api_rest.dto.CommentDTO;
import com.Nahudev.application_blog_api_rest.exceptions.BlogAppException;
import com.Nahudev.application_blog_api_rest.exceptions.ResourceNotFoundException;
import com.Nahudev.application_blog_api_rest.model.CommentEntity;
import com.Nahudev.application_blog_api_rest.model.PostEntity;
import com.Nahudev.application_blog_api_rest.repository.ICommentRepository;
import com.Nahudev.application_blog_api_rest.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService{

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IPostRepository postRepository;

    @Override
    public CommentDTO createComment(Long id_post, CommentDTO commentDTO) {

        CommentEntity comment = mapOutCommentEntity(commentDTO);
        PostEntity postEntity = postRepository.findById(id_post).orElseThrow(() ->
                new ResourceNotFoundException("Publicacion", "id", id_post));

        comment.setPostEntity(postEntity);
        CommentEntity newComment = commentRepository.save(comment);

        return mapOutCommentDTO(newComment);
    }

    @Override
    public List<CommentDTO> getAllCommentsByPost(Long id_post) {

        List<CommentEntity> comments = commentRepository.findByPostEntityId(id_post);
        return comments.stream().map(comment -> mapOutCommentDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentByPost(Long id_post, Long id_comment) {

        PostEntity postEntity = postRepository.findById(id_post).orElseThrow(() ->
                new ResourceNotFoundException("Publicacion", "id", id_post));

        CommentEntity comment = commentRepository.findById(id_comment).orElseThrow(() ->
                new ResourceNotFoundException("Comentario", "id", id_comment));

        if (!comment.getPostEntity().getId().equals(postEntity.getId())) {
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        return mapOutCommentDTO(comment);
    }

    @Override
    public CommentDTO editComment(Long id_post, Long id_comment, CommentDTO commentRequest) {

        PostEntity postEntity = postRepository.findById(id_post).orElseThrow(() ->
                new ResourceNotFoundException("Publicacion", "id", id_post));

        CommentEntity comment = commentRepository.findById(id_comment).orElseThrow(() ->
                new ResourceNotFoundException("Comentario", "id", id_comment));

        if (!comment.getPostEntity().getId().equals(postEntity.getId())) {
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        CommentEntity commentEdited = commentRepository.save(comment);

        return mapOutCommentDTO(commentEdited);
    }

    public CommentDTO mapOutCommentDTO(CommentEntity commentEntity) {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setName(commentEntity.getName());
        commentDTO.setEmail(commentEntity.getEmail());
        commentDTO.setBody(commentEntity.getBody());

        return commentDTO;
    }

    public CommentEntity mapOutCommentEntity(CommentDTO commentDTO) {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setName(commentDTO.getName());
        commentEntity.setEmail(commentDTO.getEmail());
        commentEntity.setBody(commentDTO.getBody());

        return commentEntity;
    }

}
