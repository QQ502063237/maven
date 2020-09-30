package com.maven.namesilo7005.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> implements Serializable {

    private String result;
    private String message;
    private T data;


}
