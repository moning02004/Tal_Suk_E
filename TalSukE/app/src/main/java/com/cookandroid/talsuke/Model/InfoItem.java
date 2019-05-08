package com.cookandroid.talsuke.Model;

public class InfoItem {
    private String title;
    private String created;
    private String content;

    public InfoItem(String title, String created, String content) {
        this.title = title;
        this.created = created;
        this.content = content;
    }

    public String getTitle() {return title;}
    public String getCreated() {return created;}
    public String getContent() {return content;}
    public void setTitle(String title) {this.title = title;}
    public void setCreated(String created){this.created = created;}
    public void setContent(String content) {this.content = content;}
}
