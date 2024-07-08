package com.Nahudev.application_blog_api_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    List<PostEntityDTO> content;

    private int pageNumber;

    private int pageSize;

    private Long totalItems;

    private int totalPages;

    private boolean last;

}
