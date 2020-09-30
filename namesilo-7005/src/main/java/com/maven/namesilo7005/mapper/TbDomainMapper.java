package com.maven.namesilo7005.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maven.common.entity.namesilo7005.TbDomain;
import com.maven.namesilo7005.dto.DomainDto;
import com.maven.namesilo7005.vo.TbDomainVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TbDomainMapper extends BaseMapper<TbDomain> {

    //    @Select("select * from tb_domain")
    IPage<TbDomainVo> pageDetails(Page<TbDomainVo> page, @Param("domainDto") DomainDto domainDto);



    int add(@Param("tbDomain") TbDomain tbDomain);

    void addList(@Param("tbDomains") List<TbDomain> tbDomains);


    @Select("select * from tb_domain")
    List<TbDomainVo> getList();
}
