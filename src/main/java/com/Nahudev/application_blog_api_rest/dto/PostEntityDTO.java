package com.Nahudev.application_blog_api_rest.dto;

import com.Nahudev.application_blog_api_rest.model.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntityDTO {

    private Long id;

    private String title;

    private String description;

    private String content;

    private Set<CommentEntity> Comments;

}
