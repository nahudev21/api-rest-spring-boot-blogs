package com.Nahudev.application_blog_api_rest.dto;

import com.Nahudev.application_blog_api_rest.model.CommentEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty
    @Size(min = 2, message = "El titulo debe tener al menos 2 caracteres")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "La descripcion debe tener al menos 10 caracteres")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentEntity> Comments;

}
