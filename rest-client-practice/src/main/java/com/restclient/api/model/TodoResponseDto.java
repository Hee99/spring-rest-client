package com.webclient.api.model;

import lombok.Data;

import java.util.List;

@Data
public class TodoResponseDto {

    private List<TodoDto> todos;

    private Integer total;

    private Integer skip;

    private Integer limit;

}
