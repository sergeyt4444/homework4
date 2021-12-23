package com.homework.mail;

public class Mail {
    private String email;
    private String title;
    private String message;

    public Mail(String email, String topic, String message) {
        this.email = email;
        this.title = topic;
        this.message = message;
    }

    public Mail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
