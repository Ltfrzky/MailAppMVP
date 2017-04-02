package com.nbs.lutfi.trymvpbutterdagger.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lutfi on 3/27/2017.
 */

public class Post {
//    private final Integer userId;
//    private final Integer id;
//    private final String title;
//    private final String body;
//
//    public Post(Integer userId, Integer id, String title, String body) {
//        this.userId = userId;
//        this.id = id;
//        this.title = title;
//        this.body = body;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getBody() {
//        return body;
//    }

    //Buat bikin yg mail

    @SerializedName("id")
    private Integer id;
    @SerializedName("isImportant")
    private Boolean isImportant;
    @SerializedName("picture")
    private String picture;
    @SerializedName("from")
    private String from;
    @SerializedName("subject")
    private String subject;
    @SerializedName("message")
    private String message;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("isRead")
    private Boolean isRead;

    public Post(Integer id, Boolean isImportant, String picture, String from, String subject, String message, String timestamp, Boolean isRead) {
        super();
        this.id = id;
        this.isImportant = isImportant;
        this.picture = picture;
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Boolean isImportant) {
        this.isImportant = isImportant;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}
