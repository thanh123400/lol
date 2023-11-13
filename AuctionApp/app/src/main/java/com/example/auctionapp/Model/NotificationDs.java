package com.example.auctionapp.Model;

import java.util.List;

public class NotificationDs {
    private String id;
    private String dateCreate;
    private String content;
    private List<PdfImage> imageContent;
    public NotificationDs() {

    }
    public NotificationDs(String id, String dateCreate, String content) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.content = content;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PdfImage> getImageContent() {
        return imageContent;
    }

    public void setImageContent(List<PdfImage> imageContent) {
        this.imageContent = imageContent;
    }
}
