package com.example.android.newsappstage1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and recceiving news data from Guardian.
 */
public final class QueryUtils {
    /**
     * Sample JSON response for a Guardian query
     */
    public static final String LOG_TAG = NewsActivity.class.getName();

    /**
     * Create a private constructor because no one should ever create a object.
     * This class is only meant to hold static variable and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String requestUrl){
        URL url = createUrl(requestUrl);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        }catch(IOException e){
            Log.e(LOG_TAG,"Problem making the HTTP request.",e);
        }
        return extractFeatureFromJson(jsonResponse);
    }
    /**
     * Return a list of {@link News} objects that hahs been built up from
     * parsing a JSOn response
     */
    public static List<News> extractFeatureFromJson(String newsJSON) {
        if (TextUtils.isEmpty(newsJSON))
        {
            return null;
        }
        List<News> news = new ArrayList<>();
        try{
            JSONObject baseJsonResonse = new JSONObject(newsJSON);
            // Extract the rot object
            JSONObject response = baseJsonResonse.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for(int i=0;i<results.length();i++)
            {
                JSONObject currentNews = results.getJSONObject(i);
                String sectionName = currentNews.getString("sectionName");
                String webTitle = currentNews.getString("webTitle");
                long webPublicationDate = currentNews.getLong("webPublicationDate");
                String url = currentNews.getString("webUrl");
                News extractedNews = new News(sectionName,webTitle,webPublicationDate,url);
                news.add(extractedNews);
            }

        }catch(JSONException e)
        {
            Log.e("QueryUtils","Problem parsing the news JSON results", e);
        }
        return news;
    }
    /**
     * Returns new URL object from the given string URL.
     */

    private static URL createUrl(String stringUrl)
    {
        URL url = null;
        try
        {
            url = new URL(stringUrl);
        }catch (MalformedURLException e)
        {
            Log.e(LOG_TAG,"Problem building the URL",e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url)throws  IOException{
        String jsonResponse="";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        //If the URL is null, then return early
        if(url == null)
        {
            return null;
        }

        try{
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG,"Problem Retrieving the guardian JSON results. ",e);
        }
        finally {
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if(inputStream != null)
            {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static  String readFromStream(InputStream inputStream)throws  IOException
    {
        StringBuilder output = new StringBuilder();
        if(inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null)
            {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
