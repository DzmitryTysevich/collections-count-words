package com.efimchick.ifmo.collections.countwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Words {
    private final List<WordNode> WORD_NODES = new ArrayList<>();
    private final StringBuilder STRING_NODES = new StringBuilder();
    private final String REGEX = "\\P{javaLetter}+";
    private final int MIN_LENGTH_WORD = 4;
    private final int MIN_AMOUNT_WORDS = 10;

    public String countWords(List<String> lines) {
        addCountedWords(lines);
        return STRING_NODES.toString();
    }

    private void addCountedWords(List<String> lines) {
        List<WordNode> wordNodes = getAllWordsWithAmount(lines);
        wordNodes.sort(new ComparatorForWordNodes());
        for (WordNode wordNode : wordNodes) {
            if (isSmallOrRareWords(wordNode))
                STRING_NODES.append(wordNode.toString());
        }
        STRING_NODES.deleteCharAt(STRING_NODES.length() - 1);
    }

    private boolean isSmallOrRareWords(WordNode wordNode) {
        return wordNode.getWord().length() >= MIN_LENGTH_WORD && wordNode.getAmountWords() >= MIN_AMOUNT_WORDS;
    }

    private List<WordNode> getAllWordsWithAmount(List<String> lines) {
        List<String> wordsList = getAllWords(lines);
        Collections.sort(wordsList);
        String comparableWord = wordsList.get(0);
        int counter = 0;
        for (String word : wordsList) {
            if (comparableWord.equals(word)) {
                counter++;
            } else {
                WORD_NODES.add(new WordNode(comparableWord, counter));
                comparableWord = word;
                counter = 1;
            }
        }
        WORD_NODES.add(new WordNode(comparableWord, counter));
        return WORD_NODES;
    }

    private List<String> getAllWords(List<String> lines) {
        List<String> lowerCaseLines = getLowerCaseLines(lines);
        List<String> wordsList = new ArrayList<>();
        for (String line : lowerCaseLines) {
            String[] words = line.split(REGEX);
            Collections.addAll(wordsList, words);
        }
        return wordsList;
    }

    private List<String> getLowerCaseLines(List<String> lines) {
        List<String> lowerCaseLines = new ArrayList<>();
        for (String line : lines) {
            lowerCaseLines.add(line.toLowerCase());
        }
        return lowerCaseLines;
    }
}