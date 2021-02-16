package com.duolingo.app.model;

public class WordMatch {

    private String word;
    private String match;

    public WordMatch(String word, String match) {
        this.word = word;
        this.match = match;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return word + " // " + match;
    }
}