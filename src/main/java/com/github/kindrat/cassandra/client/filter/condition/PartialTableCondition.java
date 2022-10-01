package com.github.kindrat.cassandra.client.filter.condition;

import java.util.Optional;
import java.util.Set;

import static com.github.kindrat.cassandra.client.util.StringUtil.lastWord;

public class PartialTableCondition implements StateCondition {
    @Override
    public boolean isCurrentState(String[] words, Set<String> columnNames) {
        Optional<String> lastWord = lastWord(words);
        return lastWord.isPresent() && columnStartsWithWord(columnNames, lastWord.get());
    }

    @Override
    public State name() {
        return State.PARTIAL_TABLE;
    }

    private boolean columnStartsWithWord(Set<String> columnNames, String lastWord) {
        return columnNames.stream().anyMatch(s -> s.startsWith(lastWord) && !s.equals(lastWord));
    }
}
