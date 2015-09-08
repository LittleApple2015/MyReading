package com.mango.myreading.utils;

import android.content.Context;

import com.mango.myreading.model.Guoke;

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
public class GetGuokeMessage {


    private static GetGuokeMessage getInformation;
    //均来自jsoup
    private Connection conn;
    private Document doc;
    private Elements elements;

    private GetGuokeMessage(Context context, String url)
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

    public static GetGuokeMessage getInstance(Context context,String url)
    {
        if(getInformation==null)
            getInformation = new GetGuokeMessage(context,url);
        return getInformation;
    }

    public List<Guoke> getGuoke()
    {
        try{
            List<Guoke> articles = new ArrayList<Guoke>();
            elements = doc.select(".article");
            //int i = 0;
            for(Element element: elements)
            {
                String title = element.select("h3").text();
                String text = element.select("h3 a").attr("abs:href");
                //获取图片的绝对路径
                String imageUrl = element.select("img").attr("abs:src");
                /*Log.e("ms", text + "" + i);
                i++;*/
                articles.add(new Guoke(title,imageUrl,text));
            }
            return articles;
        }catch (Exception e){
            e.printStackTrace();
        }
        //Log.i("ms","7");
        return null;
    }
}
