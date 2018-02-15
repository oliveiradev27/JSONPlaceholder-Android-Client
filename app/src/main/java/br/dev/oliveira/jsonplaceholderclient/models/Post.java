package br.dev.oliveira.jsonplaceholderclient.models;


import java.io.Serializable;

public class Post implements Serializable {
    private Integer id;
    private Integer userId;
    private String  title;
    private String  body;

    public Post (){
        this.setId(0);
        this.setUserId(0);
        this.setTitle("");
        this.setBody("");
    }

    public Post (Integer userId, String title, String body) {
        this.setId(0);
        this.setUserId(userId);
        this.setTitle(title);
        this.setBody(body);
    }

    public Post(Integer id, Integer userId, String title, String body) {
        this.setId(id);
        this.setUserId(userId);
        this.setTitle(title);
        this.setBody(body);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isEmpty() {
        return title.isEmpty() && body.isEmpty() && userId == 0;
    }
}
