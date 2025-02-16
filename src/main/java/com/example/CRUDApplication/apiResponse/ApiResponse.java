package com.example.CRUDApplication.apiResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // design pattern given lumbok
public class ApiResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String messsage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("StatusCode")
    private int statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Data")
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ListData")
    private List<T> listData;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("AccessToken")
    private String token;


   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("role")
   private String role;



}
