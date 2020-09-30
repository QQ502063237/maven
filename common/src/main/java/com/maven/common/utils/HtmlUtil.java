package com.maven.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HtmlUtil {
    public static List<String> domainSuffix(String htmlModel, String encoding, int length) {
        List<String> domainSuffix = new ArrayList<>();
        Document document = Jsoup.parse(htmlModel, encoding);
        Elements spans = document.select("span");
        for (Element span : spans) {
            String text = span.text();
            if (length >= text.length()-1) {
                domainSuffix.add(text);
            }
        }
        return domainSuffix;
    }
}
