package com.resttemplate.api.model;

import lombok.Data;

public class TodoRequestDto {

    @Data
    public static class Update {
        private Boolean completed;
    }

    @Data
    public static class Create {
        private String todo;

        private Boolean completed;

        private Long userId;
    }

}
