package com.example.android.newsappstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<News> {

    private String dateTime;

    public NewsAdapter(@NonNull Context context, ArrayList<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        //Find the news at the given position in the list of news
        News currentNews = getItem(position);

        // Find the textView with id section
        TextView sectionView = listItemView.findViewById(R.id.section);
        sectionView.setText(currentNews.getSection());


        // Find the textView with id title
        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(currentNews.getTitle());

        dateTime = currentNews.getTimeInMilli();
        String arrOfDateTime[] = dateTime.split("T");

        // Find the textView with id date
        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(arrOfDateTime[0]);

        TextView timeView = listItemView.findViewById(R.id.time);
        timeView.setText(arrOfDateTime[1].replace('Z',' '));

        return listItemView;
    }


}
