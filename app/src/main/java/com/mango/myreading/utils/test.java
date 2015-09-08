package com.mango.myreading.utils;

import com.mango.myreading.model.Story;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mango on 2015/9/8 0008.
 */
public class test {


    public static void main(String[] args) {

        String url = "http://news.163.com/shehui/";

        Connection conn = Jsoup.connect(url);
        try {
            Document doc = conn.data("query", "Java")//请求参数
                    .userAgent("Mozilla")//设置urer-agent
                    .cookie("auth", "token")//设置cookie
                    .timeout(50000)//设置连接超时
                    .get();//或者改为get();


           try {
                List<Story> storys = new ArrayList<Story>();
                Elements elements = doc.select(".item-top h2");
            //System.out.println(elements);
               // int i = 0;
                for (Element element : elements) {
                    String title = element.select("h2").text();
                    String textUrl = element.select("a").attr("abs:href");

                    //Log.e("ms", title + "" + i);
                    //i++;
                    System.out.println(textUrl);
                    storys.add(new Story(title, textUrl));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
