package com.mango.myreading.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.mango.myreading.R;
import com.mango.myreading.model.Guoke;

import java.util.List;

/**
 * Created by mango on 2015/9/8 0008.
 */
public class GuokeAdapter extends ArrayAdapter<Guoke> {


    //来自volley
    private ImageLoader mImageLoader;
    private NetworkImageView image;

    private int resource;
    private TextView title;


    public GuokeAdapter(Context context, int resource, List<Guoke> objects) {
        super(context, resource, objects);
        this.resource = resource;

        RequestQueue queue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(queue,new BitmapCache());

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Guoke guoke = getItem(position);
        View view;
        viewHolder viewHolder;
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
        image = (NetworkImageView) view.findViewById(R.id.image);
        //获取路径,下载图片
        String imageUrl = guoke.getImageUrl();
        image.setDefaultImageResId(R.drawable.ic_launcher);
        image.setErrorImageResId(R.drawable.ic_launcher);
        image.setImageUrl(imageUrl,mImageLoader);
        title.setText(guoke.getTitle());
        return view;
    }



    class viewHolder
    {
        //ImageView image;
        TextView title;
    }


    public class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache(){
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int cacheSize = maxMemory / 8;
            mCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url,bitmap);
        }

    }
}
