package com.example.android.newsappstage1;

public class News {

    private String section;
    private String title;
    private String timeInMilli;
    private String url;
    private String author;

    public News(String section, String title, String timeInMilli, String url, String author) {
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

    public String getTimeInMilli() {
        return String.valueOf(timeInMilli);
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }
}
