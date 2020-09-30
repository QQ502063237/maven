package com.maven.namesilo7005.entity;

import com.maven.common.entity.namesilo7005.TbDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {
    /*完成*/
    private String completed;
    /*域名集合*/
    private List<TbDomain> domains;

}
