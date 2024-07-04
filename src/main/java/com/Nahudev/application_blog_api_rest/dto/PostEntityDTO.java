package com.Nahudev.application_blog_api_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntityDTO {

    private Long post_id;

    private String title;

    private String description;

    private String content;

}
