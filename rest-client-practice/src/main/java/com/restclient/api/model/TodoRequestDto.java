package com.restclient.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class TodoRequestDto {

    @Data
    public static class Update {
        @Schema(description = "완료여부")
        private Boolean completed;
    }

    @Data
    public static class Create {
        @Schema(description = "내용")
        private String todo;

        @Schema(description = "완료여부")
        private Boolean completed;

        @Schema(description = "작성자")
        private Long userId;
    }

}
