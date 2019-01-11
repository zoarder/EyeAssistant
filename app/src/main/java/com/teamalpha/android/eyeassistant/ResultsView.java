package com.teamalpha.android.eyeassistant;



import java.util.List;

public interface ResultsView {
    public void setResults(final List<Classifier.Recognition> results);
}
