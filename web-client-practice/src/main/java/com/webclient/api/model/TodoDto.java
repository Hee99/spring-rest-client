package com.webclient.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodoDto {

    @Schema(description = "아이디")
    private Long id;

    @Schema(description = "내용")
    private String todo;

    @Schema(description = "완료여부")
    private Boolean completed;

    @Schema(description = "작성자")
    private Long userId;

}
