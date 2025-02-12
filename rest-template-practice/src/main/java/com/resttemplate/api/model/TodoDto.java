package com.resttemplate.api.model;

import lombok.Data;

@Data
public class TodoDto {

    private Long id;

    private String todo;

    private Boolean completed;

    private Long userId;

}
