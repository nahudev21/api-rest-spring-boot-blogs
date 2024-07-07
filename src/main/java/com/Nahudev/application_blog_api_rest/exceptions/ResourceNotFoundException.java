package com.Nahudev.application_blog_api_rest.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private String nameResource;

    private String nameField;

    private Long valueField;

    public ResourceNotFoundException(String nameResource, String nameField, Long valueField) {
        super(String.format("%s No encontrada con : %s : '%s'", nameResource, nameField, valueField));
        this.nameResource = nameResource;
        this.nameField = nameField;
        this.valueField = valueField;
    }
}
