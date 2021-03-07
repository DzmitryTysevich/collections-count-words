package com.efimchick.ifmo.collections.countwords;

public class WordNode {
    private int countWords;
    private String word;

    public WordNode(String word, int countWords) {
        this.countWords = countWords;
        this.word = word;
    }

    public int getAmountWords() {
        return countWords;
    }

    public void setCountWords(int countWords) {
        this.countWords = countWords;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word + " - " + countWords + "\n";
    }
}