package com.example.yiseo.bs2017;

/**
 * Created by yiseo on 2017-05-23.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.yiseo.bs2017.R.id.imageView;

public class ListViewItem {
    private String iconDrawable;
    private String titleStr;
    private String authorStr;
    private String publisherStr;


    public void setIcon(String icon) { iconDrawable = icon; }
    public void setTitle(String title) { titleStr = title ; }
    public void setAuthor(String desc) {
        authorStr = desc ;
    }
    public void setPublisher(String desc) {
        publisherStr = desc ;
    }

    public String getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getAuthor() { return this.authorStr ; }
    public String getPublisher() {
        return this.publisherStr ;
    }
}
