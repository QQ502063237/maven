package com.maven.namesilo7005.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maven.common.entity.namesilo7005.TbDomain;
import com.maven.namesilo7005.dto.DomainDto;
import com.maven.namesilo7005.vo.TbDomainVo;

import java.io.IOException;
import java.util.List;

public interface TbDomainService {

     int up(String keyWord,int length) throws IOException;

     void upAll(int length) throws IOException;


     IPage<TbDomainVo> pageDetails(Page<TbDomainVo> page, DomainDto domainDto);




     List<TbDomain> getDomains(String name,int length) throws IOException;



}
