package com.duxet.strimoid.utils;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import com.duxet.strimoid.models.*;
import com.duxet.strimoid.models.Comment;

public class Parser {
    public static String getToken(String response){
        Document doc = Jsoup.parse(response);
        return doc.getElementsByAttributeValue("name", "token").first().attr("value").toString();
    }
    
    public static ArrayList<Comment> getComments(String response) {
        ArrayList<Comment> comments = new ArrayList<Comment>();

        Document doc = Jsoup.parse(response);
        Elements elements = doc.getElementsByClass("content_comment");

        for (Element el : elements) {
            String id = el.getElementsByTag("a").first().attr("id").trim();
            String author = el.getElementsByClass("user_name").first().text().trim();
            String avatar = el.getElementsByClass("content_comment_image").first().getElementsByTag("img").first().attr("src").trim();
            String text = el.getElementsByClass("content_comment_text").first().text().trim();
            String time = el.getElementsByClass("content_comment_info").first().getElementsByAttribute("title").first().text().trim();

            boolean isReply = el.hasClass("reply");
            boolean isUpvoted = el.getElementsByClass("like").first().hasClass("selected");
            boolean isDownvoted = el.getElementsByClass("dislike").first().hasClass("selected");
            
            String likeUrl = el.getElementsByClass("like").first().attr("href");
            String dislikeUrl = el.getElementsByClass("like").first().attr("href");
            
            int up = Integer.parseInt(el.getElementsByClass("like").first().getElementsByClass("content_comment_vote_count").text());
            int down = Integer.parseInt(el.getElementsByClass("dislike").first().getElementsByClass("content_comment_vote_count").text());

            Comment comment = new Comment(id, author, avatar, text, time, likeUrl, dislikeUrl,
                    up, down, isUpvoted, isDownvoted, isReply);
            comments.add(comment);
        }

        return comments;
    }
    
    public static ArrayList<Content> getContents(String response) {
        ArrayList<Content> contents = new ArrayList<Content>();

        Document doc = Jsoup.parse(response);
        Elements elements = doc.getElementsByClass("content");

        for (Element el : elements) {
            String id = el.getElementsByTag("a").first().attr("id").trim();
            String title = el.getElementsByClass("content_title").first().text().trim();
            String author = el.getElementsByClass("user_name").first().getElementsByTag("span").first().text().trim();
            String desc = el.getElementsByClass("content_info").text().trim();
            String url = el.getElementsByClass("content_title").first().attr("href").trim();
            String commentsUrl = el.getElementsByClass("content_info_actions").first().getElementsByTag("a").first().attr("href").trim();

            String imageUrl = "";

            if (el.getElementsByClass("content_image").first() != null)
                imageUrl = el.getElementsByClass("content_image").first().getElementsByTag("img").first().attr("src").trim();
            
            boolean isUpvoted = el.getElementsByClass("like").first().hasClass("selected");
            boolean isDownvoted = el.getElementsByClass("dislike").first().hasClass("selected");
            
            String likeUrl = el.getElementsByClass("like").first().attr("href");
            String dislikeUrl = el.getElementsByClass("like").first().attr("href");

            int up = Integer.parseInt(el.getElementsByClass("like").first().getElementsByClass("content_vote_count").text());
            int down = Integer.parseInt(el.getElementsByClass("dislike").first().getElementsByClass("content_vote_count").text());

            Content content = new Content(id, title, author, desc, url, imageUrl, commentsUrl,
                    likeUrl, dislikeUrl, up, down, isUpvoted, isDownvoted);
            contents.add(content);
        }

        return contents;
    }

    public static ArrayList<Entry> getEntries(String response) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        Document doc = Jsoup.parse(response);
        Elements elements = doc.getElementsByClass("entry");

        for (Element el : elements) {
            String id = el.getElementsByTag("a").first().attr("id").trim();
            String author = el.getElementsByClass("entry_user").first().text().trim();
            String avatar = el.getElementsByClass("entry_image").first().getElementsByTag("img").first().attr("src").trim();
            String message = el.getElementsByClass("entry_text").first().text().trim();
            String time = el.getElementsByClass("entry_info").first().getElementsByAttribute("title").first().text().trim();
            String strim = el.getElementsByClass("entry_info").first().getElementsByTag("a").first().text().trim();

            boolean isReply = el.hasClass("reply");
            boolean isUpvoted = el.getElementsByClass("like").first().hasClass("selected");
            boolean isDownvoted = el.getElementsByClass("dislike").first().hasClass("selected");
            
            String likeUrl = el.getElementsByClass("like").first().attr("href");
            String dislikeUrl = el.getElementsByClass("like").first().attr("href");
            
            int up = Integer.parseInt(el.getElementsByClass("like").first().getElementsByClass("entry_vote_count").text());
            int down = Integer.parseInt(el.getElementsByClass("dislike").first().getElementsByClass("entry_vote_count").text());

            Entry entry = new Entry(id, author, avatar, message, time, strim, likeUrl, dislikeUrl,
                    up, down, isUpvoted, isDownvoted, isReply);
            entries.add(entry);
        }

        return entries;
    }
    
    public static ArrayList<Strim> getStrims(String response) {
        ArrayList<Strim> strims = new ArrayList<Strim>();
        
        String content = "";
        
        try {
            JSONObject json = new JSONObject(response);
            content = json.getString("content");
        } catch (JSONException e) {
            return null;
        }
        
        Document doc = Jsoup.parse(content);
        Elements elements = doc.getElementsByTag("li");
        
        for (Element el : elements) {
            String name = el.getElementsByTag("a").first().attr("href").trim();
            String title = el.getElementsByClass("name").first().text().trim();
            String desc = "";
            
            Strim strim = new Strim(name, title, desc);
            strims.add(strim);
        }

        return strims;
    }
}
