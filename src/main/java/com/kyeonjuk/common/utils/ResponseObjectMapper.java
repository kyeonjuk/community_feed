package com.kyeonjuk.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyeonjuk.common.ui.Response;

public class ResponseObjectMapper {

    public ResponseObjectMapper() {}

    private static final ObjectMapper objMapper = new ObjectMapper();

    // JSON -> 객체
    public static Response toResponseObject(String response) {

        try {
            // .readValue(JSON문자열, 변환할 클래스타입.class);
            return objMapper.readValue(response, Response.class);
        } catch (Exception e) {
            return null;
        }
    }

    // 객체 -> JSON(String)
    public static String toStringResponse(Response<?> response) {

        try {
            return objMapper.writeValueAsString(response);
        } catch (Exception e) {
            return null;
        }
    }

}
