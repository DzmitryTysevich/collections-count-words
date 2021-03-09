package com.efimchick.ifmo.collections.countwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Words {
    private static final String REGEX = "\\P{javaLetter}+";
    private static final int MIN_LENGTH_WORD = 4;
    private static final int MIN_AMOUNT_WORDS = 10;

    public String countWords(List<String> lines) {
        return addCountedWords(lines).toString();
    }

    private StringBuilder addCountedWords(List<String> lines) {
        List<WordNode> wordNodes = getAllWordsWithAmount(lines);
        StringBuilder stringNodes = new StringBuilder();
        wordNodes.sort(new ComparatorForWordNodes());
        for (WordNode wordNode : wordNodes) {
            if (isSmallOrRareWords(wordNode))
                stringNodes.append(wordNode.toString());
        }
        stringNodes.deleteCharAt(stringNodes.length() - 1);
        return stringNodes;
    }

    private boolean isSmallOrRareWords(WordNode wordNode) {
        return wordNode.getWord().length() >= MIN_LENGTH_WORD && wordNode.getAmountWords() >= MIN_AMOUNT_WORDS;
    }

    private List<WordNode> getAllWordsWithAmount(List<String> lines) {
        List<WordNode> allWordsWithAmount = new ArrayList<>();
        List<String> wordsList = getAllWords(lines);
        Collections.sort(wordsList);
        String comparableWord = wordsList.get(0);
        int counter = 0;
        for (String word : wordsList) {
            if (comparableWord.equals(word)) {
                counter++;
            } else {
                allWordsWithAmount.add(new WordNode(comparableWord, counter));
                comparableWord = word;
                counter = 1;
            }
        }
        allWordsWithAmount.add(new WordNode(comparableWord, counter));
        return allWordsWithAmount;
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