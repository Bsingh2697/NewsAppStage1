package com.example.android.newsappstage1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String GUARDIAN_URL="https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=aba45bca-ce0b-47c9-9a3e-28c79fe716ab";
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_item);

        //Find a reference to the {@link ListView} in the layout
        ListView newsListView = findViewById(R.id.news_list);

        //Create a new ArrayAdapter of earthquakes
        adapter = new NewsAdapter(this,new ArrayList<News>());
        newsListView.setAdapter(adapter);


    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        return  new NewsLoader(this,GUARDIAN_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {

        adapter.clear();
        if(data != null && !data.isEmpty()){
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        adapter.clear();
    }
}
