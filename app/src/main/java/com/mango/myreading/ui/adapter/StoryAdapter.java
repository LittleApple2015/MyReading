package com.mango.myreading.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.myreading.R;
import com.mango.myreading.model.Story;

import java.util.List;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class StoryAdapter extends ArrayAdapter<Story>{

    private int resource;
    private TextView title;
    private ImageView imageView;


    public StoryAdapter(Context context, int resource, List<Story> objects) {
        super(context, resource, objects);
        this.resource = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Story story = getItem(position);
        View view;
       // viewHolder viewHolder;
        if(convertView==null)
        {
            view = LayoutInflater.from(getContext()).inflate(resource,null);
           /* viewHolder = new viewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(viewHolder); //将viewHolder存储在view中*/
        }
        else
        {
            view = convertView;
            //viewHolder = (ArticleAdapter.viewHolder) view.getTag(); //重新获取viewHolder
        }
        //viewHolder.title.setText(article.getTitle());
        //viewHolder.image.setImageResource(R.drawable.tango);
        title = (TextView) view.findViewById(R.id.title);
        imageView = (ImageView) view.findViewById(R.id.image);
        title.setText(story.getTitle());
        imageView.setImageResource(R.drawable.ic_launcher);
        return view;
    }

}
