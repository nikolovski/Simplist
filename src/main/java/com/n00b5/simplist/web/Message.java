package com.n00b5.simplist.web;

/**
 * Project: simplist
 *
 * @author d4k1d23
 * @date 1/10/17
 */
public class Message {
    private String title;
    private String description;

    public Message() {
    }

    public Message(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
