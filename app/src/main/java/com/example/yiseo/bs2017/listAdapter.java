package com.example.yiseo.bs2017;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by HyoChun on 2017-05-22.
 */

public class listAdapter extends BaseAdapter{

    private Context context;
    LayoutInflater inflater;
    Book item = new Book();

    public listAdapter(Context context) {
        setContext(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter, parent, false);

        TextView albumTitle = (TextView) view.findViewById(R.id.Title);
        TextView songTitle = (TextView) view.findViewById(R.id.Author);
        ImageView albumImg = (ImageView) view.findViewById(R.id.Searchimage);
        albumTitle.setText("책제목 : "+ item.getTitle());
        songTitle.setText("저자 : " + item.getAuthor());

        try {
            URL url = new URL(item.getImag());
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            albumImg.setImageBitmap(bm);
        } catch (Exception e) {
        }

        return view;
    }
}
