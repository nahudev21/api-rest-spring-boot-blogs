package com.Nahudev.application_blog_api_rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;

    @NotEmpty(message = "El email no puede estar vacio")
    @Email
    private String email;

    @NotEmpty(message = "El cuerpo no puede estar vacio")
    @Size(min = 1, message = "")
    private String body;

}
