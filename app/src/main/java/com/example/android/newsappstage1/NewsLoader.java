package com.example.android.newsappstage1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>>{

    private String Url;
    public NewsLoader(Context context, String url)
    {
        super(context);
        Url=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        if(Url == null)
        {
            return null;
        }
        return QueryUtils.fetchNewsData(Url);
    }
}
