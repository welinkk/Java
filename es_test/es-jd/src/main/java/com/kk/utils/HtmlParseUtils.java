package com.kk.utils;

import com.kk.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@Component
public class HtmlParseUtils {
    public static void main(String[] args) throws IOException {
        new HtmlParseUtils().parseJD("java").forEach(System.out::println);

    }
    public ArrayList<Content> parseJD(String keyworks) throws IOException {
        //获取请求地址https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=b30300084a6b4cbc97d92f6cb4af6d0e
        String url = "https://search.jd.com/Search?keyword="+keyworks;
        //解析网页。（Jsoup返回Document就是浏览器Document对象）
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有你在js中可以使用的方法，这里都能用！
        Element element = document.getElementById("J_goodsList");
//        System.out.println(element.html());

        ArrayList<Content> goodList = new ArrayList<>();
        Elements elements = element.getElementsByTag("li");
        //获取元素中的内容，这里el,就是每个li标签了
        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setTitle(title);
            content.setImg(img);
            content.setPrice(price);
            goodList.add(content);

        }
        return goodList;
    }
}
