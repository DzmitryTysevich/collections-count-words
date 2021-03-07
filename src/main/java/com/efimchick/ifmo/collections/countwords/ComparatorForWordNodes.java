package com.efimchick.ifmo.collections.countwords;

import java.util.Comparator;

public class ComparatorForWordNodes implements Comparator<WordNode> {
    @Override
    public int compare(WordNode o1, WordNode o2) {
        return o2.getAmountWords() - o1.getAmountWords();
    }
}