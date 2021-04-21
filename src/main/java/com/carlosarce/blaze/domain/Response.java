package com.carlosarce.blaze.domain;

import lombok.Data;

@Data
public class Response {
    private String code;
    private String message;
    private Object data;
}
