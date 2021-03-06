package com.mango.myreading.utils;

import android.content.Context;

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
 * Created by Administrator on 2015/9/8 0008.
 */
public class GetStoryMessage {

    private static GetStoryMessage getStoryMessage;
    //均来自jsoup
    private Connection conn;
    private Document doc;
    private Elements elements;

    private GetStoryMessage(Context context, String url)
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

    public static GetStoryMessage getInstance(Context context,String url)
    {
        if(getStoryMessage==null)
            getStoryMessage = new GetStoryMessage(context,url);
        return getStoryMessage;
    }

    public List<Story> getStory()
    {

        try {
            List<Story> storys = new ArrayList<Story>();
            Elements elements = doc.select(".rlist ul li"); //注意标签的使用
            for (Element element : elements) {
                String title = element.select("li").text();
                String textUrl = element.select("a").attr("abs:href");
                storys.add(new Story(title, textUrl));
            }
            return storys;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
