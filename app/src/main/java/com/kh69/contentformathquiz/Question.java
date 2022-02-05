package com.kh69.contentformathquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {
        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("qtn_text")
        @Expose
        private String text;

        @SerializedName("year")
        @Expose
        private int year;

        @SerializedName("paper")
        @Expose
        private int paper;

        @SerializedName("section")
        @Expose
        private String section;

        @SerializedName("topic")
        @Expose
        private String topic;

        @SerializedName("answer")
        @Expose
        private String answer;

        @SerializedName("katex_question")
        @Expose
        private String katex_question;

        @SerializedName("katex_answer")
        @Expose
        private String katex_answer;

        @SerializedName("edited")
        @Expose
        private boolean edited;

    public Question(String text, int year, int paper, String section, String topic, String answer, String katex_question, String katex_answer, boolean edited) {
        this.text = text;
        this.year = year;
        this.paper = paper;
        this.section = section;
        this.topic = topic;
        this.answer = answer;
        this.katex_question = katex_question;
        this.katex_answer = katex_answer;
        this.edited = edited;
    }

    public boolean getEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getKatex_question() {
        return katex_question;
    }

    public void setKatex_question(String katex_question) {
        this.katex_question = katex_question;
    }

    public String getKatex_answer() {
        return katex_answer;
    }

    public void setKatex_answer(String katex_answer) {
        this.katex_answer = katex_answer;
    }
}
