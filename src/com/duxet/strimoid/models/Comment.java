package com.duxet.strimoid.models;

public class Comment {
    
    String id, author, avatar, text, time;
    String likeUrl, dislikeUrl;
    int upvotes, downvotes;
    boolean isUpvoted, isDownvoted, isReply;

    public Comment(String id, String author, String avatar, String text,
            String time, String likeUrl, String dislikeUrl, int upvotes,
            int downvotes, boolean isUpvoted, boolean isDownvoted,
            boolean isReply) {
        super();
        this.id = id;
        this.author = author;
        this.avatar = avatar;
        this.text = text;
        this.time = time;
        this.likeUrl = likeUrl;
        this.dislikeUrl = dislikeUrl;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.isUpvoted = isUpvoted;
        this.isDownvoted = isDownvoted;
        this.isReply = isReply;
    }
    
    public String getId() {
        return id;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public String getText() {
        return text;
    }
    
    public String getTime() {
        return time;
    }
    
    public String getLikeUrl() {
        return likeUrl;
    }
    
    public String getDislikeUrl() {
        return dislikeUrl;
    }
    
    public int getUpvotes() {
        return upvotes;
    }
    
    public int getDownvotes() {
        return downvotes;
    }
    
    public boolean isUpvoted() {
        return isUpvoted;
    }
    
    public boolean isDownvoted() {
        return isDownvoted;
    }
    
    public boolean isReply() {
        return isReply;
    }

    public void setUpvoted(boolean isUpvoted) {
        this.isUpvoted = isUpvoted;
    }

    public void setDownvoted(boolean isDownvoted) {
        this.isDownvoted = isDownvoted;
    }

}
