package com.maven.namesilo7005.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maven.common.entity.namesilo7005.TbDomain;
import com.maven.common.utils.HtmlUtil;
import com.maven.common.utils.HttpClientUtil;
import com.maven.common.utils.ReadFileUtil;
import com.maven.namesilo7005.dto.DomainDto;
import com.maven.namesilo7005.entity.CheckIdData;
import com.maven.namesilo7005.entity.DataResponse;
import com.maven.namesilo7005.entity.ResultData;
import com.maven.namesilo7005.mapper.TbDomainMapper;
import com.maven.namesilo7005.service.TbDomainService;
import com.maven.namesilo7005.utils.RequestParameterUtil;
import com.maven.namesilo7005.vo.TbDomainVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TbDomainServiceImpl implements TbDomainService {
    @Resource
    TbDomainMapper tbDomainMapper;

    @Override
    public int up(String keyWord,int length) throws IOException {
        List<TbDomain> tbDomainList = getDomains(keyWord,length);
        tbDomainMapper.addList(tbDomainList);
//        for (TbDomain tbDomain : tbDomainList) {
//            tbDomainMapper.add(tbDomain);
//        }
        return 0;
    }

    @Override
    public void upAll(int length) throws IOException {
        //生产域名前缀
        ArrayList<String> keyWordList = new ArrayList<>();

        for(String keyWord: keyWordList ){
            List<TbDomain> tbDomainList = getDomains(keyWord,length);
            for (TbDomain tbDomain : tbDomainList) {
                //有就更新,木有就添加
                if (null != tbDomainMapper.selectOne(new QueryWrapper<TbDomain>().eq("domain", tbDomain.getDomain()))) {
                    tbDomainMapper.update(tbDomain, new UpdateWrapper<TbDomain>().eq("domain", tbDomain.getDomain()));
                } else {
                    tbDomainMapper.insert(tbDomain);
                }
            }
        }


    }

    @Override
    public IPage<TbDomainVo> pageDetails(Page<TbDomainVo> page, DomainDto domainDto) {
        IPage<TbDomainVo> tbDomainVoIPage = tbDomainMapper.pageDetails(page,domainDto);
        return tbDomainVoIPage;
    }

    @Override
    public List<TbDomain> getDomains(String name,int length) throws IOException {
        //读取域名后缀文件
        String htmlModel = ReadFileUtil.readLine("D:\\new_file.html", "UTF-8");
        //获得域名后缀集合
        List<String> domainSuffixList = HtmlUtil.domainSuffix(htmlModel, "UTF-8",length);
        //工具创建form表数据
        Map<String, List<String>> requestMap = RequestParameterUtil.getForms(name, domainSuffixList);
        //工具请求
        String responseStr = HttpClientUtil.Post("https://www.namesilo.com/public/api/domains/bulk-check", requestMap);
        //返回json字符串转为实体bean
        DataResponse dataResponse1 = JSONUtil.toBean(responseStr, DataResponse.class);
        Object checkData = dataResponse1.getData();
        //转换实体
        CheckIdData checkIdData = JSONObject.parseObject(JSONObject.toJSONString(checkData), CheckIdData.class);
        System.out.println("checkId实体类" + checkIdData.toString());
        //获取id
        String checkId = checkIdData.getCheckId();
        //获取数据get
        String resp = HttpUtil.get("https://www.namesilo.com/public/api/domains/results/" + checkId);
        DataResponse dataResponse2 = JSONUtil.toBean(resp, DataResponse.class);
        Object resultDataObj = dataResponse2.getData();
        //转换实体
        ResultData resultData = JSONObject.parseObject(JSONObject.toJSONString(resultDataObj), ResultData.class);
        List<TbDomain> domains = resultData.getDomains();
        return domains;
    }


}
