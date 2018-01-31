package br.dev.oliveira.jsonplaceholderclient.models;


public class Post {
    private Integer id;
    private Integer userId;
    private String  title;
    private String  body;

    public Post (){}

    public Post (Integer userId, String title, String body) {
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
}
