package com.maven.namesilo7005.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckIdData {

    private String checkId;
    private List<String> domains=new ArrayList<>();

    private  String hasNextPage;
    private int pageSize;
    private Integer pageNumber;
    private  Integer pageCount;
    private  Integer domainCount;


}
