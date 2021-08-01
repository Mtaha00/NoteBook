package com.example.noteuniversity;

public class Note {
    private String title;
    private String text;
    private String textDate;

    public Note(String title, String text, String textDate) {
        this.title = title;
        this.text = text;
        this.textDate = textDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }
}
