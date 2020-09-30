package com.maven.namesilo7005.utils;


import java.util.*;

public class RequestParameterUtil {
    public static Map<String , List<String>>getForms(String inputName,List<String> domainSuffixList){
        Map<String , List<String>> requestMap = new HashMap<>();

        List<String> domains = new ArrayList<>();
        for (String domainSuffix: domainSuffixList){
            domains.add(inputName+domainSuffix);
        }

        List<String> tlds = new ArrayList<>();
        for (String domainSuffix: domainSuffixList){
            domainSuffix=domainSuffix.replace(".","");
            tlds.add(domainSuffix);
        }

        requestMap.put("domains",domains);
        requestMap.put("tlds",tlds);
        return  requestMap;
    }
}
