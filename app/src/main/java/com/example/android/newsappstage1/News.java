package com.example.android.newsappstage1;

public class News {

    private String section;
    private String title;
    private long timeInMilli;

    private String url;

    public News(String section, String title, long timeInMilli, String url)
    {
      this.section=section;
      this.title=title;
      this.timeInMilli=timeInMilli;
      this.url=url;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public long getTimeInMilli() {
        return timeInMilli;
    }

    public String getUrl() {
        return url;
    }
}
