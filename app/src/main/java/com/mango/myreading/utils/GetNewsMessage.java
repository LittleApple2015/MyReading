package com.mango.myreading.utils;

import android.content.Context;

import com.mango.myreading.model.News;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class GetNewsMessage {

    private static GetNewsMessage getNewsMessage;
    //均来自jsoup
    private Connection conn;
    private Document doc;
    private Elements elements;

    private GetNewsMessage(Context context, String url)
    {
        conn = Jsoup.connect(url);
        try {
            doc = conn.data("query", "Java")//请求参数
                    .userAgent("Mozilla")//设置urer-agent
                    .cookie("auth", "token")//设置cookie
                    .timeout(50000)//设置连接超时
                    .get();//或者改为get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GetNewsMessage getInstance(Context context,String url)
    {
        if(getNewsMessage==null)
            getNewsMessage = new GetNewsMessage(context,url);
        return getNewsMessage;
    }

    public List<News> getNews()
    {

        try {
            List<News> Newss = new ArrayList<News>();
            Elements elements = doc.select(".item-top h2");
            for (Element element : elements) {
                String title = element.select("h2").text();
                String textUrl = element.select("a").attr("abs:href");
                Newss.add(new News(title, textUrl));
            }
            return Newss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
