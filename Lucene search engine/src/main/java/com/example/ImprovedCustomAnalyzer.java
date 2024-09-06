package com.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.CharArraySet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.io.IOException

public class ImprovedCustomAnalyzer extends Analyzer {

    public static final String COUNTRY_COLLECTION_PATH = "./data/CustimoisedAnalyzerCorpus/world_countries.txt";
    public static final String COMMON_WORD_PATH = "./data/CustimoisedAnalyzerCorpus/common_word_list.txt";
    public static final String DRUG_SYN_PATH = "./data/CustimoisedAnalyzerCorpus/drugSynnous.txt";
    public static final String DISEASE_SYN_PATH = "./data/CustimoisedAnalyzerCorpus/diseaseSynomous.txt";

    private final CharArraySet stopWordsSet;
    private final SynonymMap countrySynonyms;
    private final SynonymMap commonWordSynonyms;
    private final SynonymMap drugSynonyms;
    private final SynonymMap diseaseSynonyms;

    public ImprovedCustomAnalyzer() {
        // Load stop words from file
        this.stopWordsSet = loadStopWords(COMMON_WORD_PATH);

        // Load synonyms from files
        this.countrySynonyms = loadSynonyms(COUNTRY_COLLECTION_PATH);
        this.commonWordSynonyms = loadSynonyms(COMMON_WORD_PATH);
        this.drugSynonyms = loadSynonyms(DRUG_SYN_PATH);
        this.diseaseSynonyms = loadSynonyms(DISEASE_SYN_PATH);
    }

    private CharArraySet loadStopWords(String path) {
        try {
            List<String> stopWordsList = Files.readAllLines(Paths.get(path));
            return new CharArraySet(stopWordsList, true);
        } catch (IOException e) {
            // Handle the exception (e.g., log it or throw a runtime exception)
            e.printStackTrace();
            return new CharArraySet(Arrays.asList(), true); // Return an empty set in case of failure
        }
    }

    private SynonymMap loadSynonyms(String path) {
        try {
            // Implement your logic to create a SynonymMap from the file content
            // This might involve reading the file, parsing its content, and building a SynonymMap
            // For simplicity, I'm returning null here; you need to implement this logic.
            return null;
        } catch (IOException e) {
            // Handle the exception (e.g., log it or throw a runtime exception)
            e.printStackTrace();
            return null; // Return null in case of failure
        }
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new StandardTokenizer();
        LowerCaseFilter lowerCaseFilter = new LowerCaseFilter(source);
        StopFilter stopFilter = new StopFilter(lowerCaseFilter, stopWordsSet);
        SynonymGraphFilter countrySynonymFilter = new SynonymGraphFilter(stopFilter, countrySynonyms, true);
        SynonymGraphFilter commonWordSynonymFilter = new SynonymGraphFilter(countrySynonymFilter, commonWordSynonyms, true);
        SynonymGraphFilter drugSynonymFilter = new SynonymGraphFilter(commonWordSynonymFilter, drugSynonyms, true);
        SynonymGraphFilter diseaseSynonymFilter = new SynonymGraphFilter(drugSynonymFilter, diseaseSynonyms, true);
        PorterStemFilter stemFilter = new PorterStemFilter(diseaseSynonymFilter);

        return new TokenStreamComponents(source, stemFilter);
    }
}
