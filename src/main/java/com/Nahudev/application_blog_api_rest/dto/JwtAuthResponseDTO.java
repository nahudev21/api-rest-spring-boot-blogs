package com.Nahudev.application_blog_api_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponseDTO {

    private String accesToken;

    private String tokenType = "Bearer";

    public JwtAuthResponseDTO(String accesToken) {
        this.accesToken = accesToken;
    }
}
