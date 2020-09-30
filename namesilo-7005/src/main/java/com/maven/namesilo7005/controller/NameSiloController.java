package com.maven.namesilo7005.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maven.common.vo.HttpResult;
import com.maven.namesilo7005.dto.DomainDto;
import com.maven.namesilo7005.schedule.DomainTask;
import com.maven.namesilo7005.service.TbDomainService;
import com.maven.namesilo7005.vo.TbDomainVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/nameSilo/data")
public class NameSiloController {
    @Resource
    DomainTask domainTask;
    @Resource
    TbDomainService tbDomainService;

    @GetMapping("/page")
    public HttpResult<IPage<TbDomainVo>> page(DomainDto domainDto, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                              @RequestParam(name = "pageSize", defaultValue = "50") Integer pageSize) {
        HttpResult<IPage<TbDomainVo>> result = new HttpResult<>();
//        try {
            Page<TbDomainVo> page = new Page<>(pageNo, pageSize);
            IPage<TbDomainVo> domainIPage = tbDomainService.pageDetails(page, domainDto);
            result.setData(domainIPage);
            result.success("查询成功");
            return result;
//        } catch (Exception e) {
//            result.error("查询失败");
//            return result;
//        }
    }

    @GetMapping("/up/{keyWord}")
    public HttpResult<Object> up(@PathVariable("keyWord") String keyWord) throws IOException {
        HttpResult<Object> result = new HttpResult<>();
//        try {
            tbDomainService.up(keyWord,3);
            result.setMessage("更新指定"+keyWord+"成功");
            return result;
//        } catch (Exception e) {
//            result.setMessage("更新指定"+keyWord+"失败");
//            return result;
//        }
    }

    @GetMapping("/upAll")
    public HttpResult<Object> upAll() {
        HttpResult<Object> result = new HttpResult<>();
        try {
            tbDomainService.upAll(3);
            result.setMessage("更新全部成功");
            return result;
        } catch (Exception e) {
            result.setMessage("更新全部失败");
            return result;
        }
    }

}
