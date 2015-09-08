package com.mango.myreading.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mango.myreading.R;
import com.mango.myreading.model.News;
import com.mango.myreading.ui.ArticleText;
import com.mango.myreading.ui.adapter.NewsAdapter;
import com.mango.myreading.utils.GetNewsMessage;

import java.util.List;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class NewsFragment extends android.support.v4.app.Fragment {

    private View mMainView;

    private static final String URL = "http://news.163.com/shehui/";
    private ListView listView;
    private GetNewsMessage getNewsMessage;
    private NewsAdapter newsAdapter;
    private List<News> newss;
    private Intent intent;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = getActivity().getLayoutInflater();
        //viewGroup是viewpager
        mMainView = inflater.inflate(R.layout.news_fragment, (ViewGroup) getActivity().findViewById(R.id.viewpager),false);

        initView();
        getdata();
        addClick();

    }



    private void addClick()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News news = newss.get(position);
                String textUrl = news.getTextUrl();

                intent = new Intent(getActivity(), ArticleText.class);
                bundle = new Bundle();
                bundle.putString("textUrl", textUrl);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getdata() {
        new Thread(){
            @Override
            public void run() {
                super.run(); //处理耗时操作..下载等
                getNewsMessage = GetNewsMessage.getInstance(getActivity(), URL);
                newss = getNewsMessage.getNews();
                newsAdapter = new NewsAdapter(getActivity(),R.layout.article_item,newss);

                //Log.e("ms","1");
                //注意这里是fragment 必须加上getActivity才能改变视图
                getActivity().runOnUiThread(new Runnable() { //更新ui
                    @Override
                    public void run() {
                        try{
                            listView.setAdapter(newsAdapter);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
            }
        }.start();
    }


    private void initView()
    {
        listView = (ListView) mMainView.findViewById(R.id.news_listview);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup p = (ViewGroup) mMainView.getParent();  //??
        if(p!=null){
            p.removeAllViewsInLayout();
        }
        return mMainView;
    }
}
