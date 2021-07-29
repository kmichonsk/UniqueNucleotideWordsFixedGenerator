package org.example.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


public class UniqueNucleotideWordsGenerator {

    private static final int NUCLEOTIDE_WORD_LENGTH = 6;
    private static final int MINIMUM_NUCLEOTIDE_WORD_DIFFERENCE = 3;
    private static final char[] NUCLEOTIDES = {'A', 'T', 'G', 'C'};
    private static final int MAXIMUM_POSSIBLE_PERMUTATIONS = (int) Math.pow(NUCLEOTIDES.length, NUCLEOTIDE_WORD_LENGTH);

    private UniqueNucleotideWordsGenerator() {
        throw new AssertionError("For simplicity (no di framework) " +
                "this should be used as an utility class!");
    }

    public static List<String> generateUniqueNucleotideWords(int numberOfWordsToGenerate) {
        var list = new ArrayList<String>(numberOfWordsToGenerate);
        var triedWords = new HashSet<String>();

        while (list.size() < numberOfWordsToGenerate) {
            if (triedWords.size() == MAXIMUM_POSSIBLE_PERMUTATIONS) {
                // deadlock found, naive solution
                list = new ArrayList<>(numberOfWordsToGenerate);
                triedWords = new HashSet<>();
                continue;
            }

            final var word = generateRandomNucleotideWord();
            if (triedWords.contains(word)) {
                continue;
            }
            triedWords.add(word);

            if (!containsTooSimilar(list, word)) {
                list.add(word);
            }
        }

        return list;
    }

    private static boolean containsTooSimilar(List<String> list, String nucleotide) {
        return list.stream()
                .anyMatch(listNucleotide -> !meetDifferenceCriteria(listNucleotide, nucleotide));
    }

    public static boolean meetDifferenceCriteria(String first, String second) {
        int difference = 0;
        for (int i = 0; i < NUCLEOTIDE_WORD_LENGTH; ++i) {
            if (first.charAt(i) != second.charAt(i)) {
                ++difference;
            }
        }
        return difference >= MINIMUM_NUCLEOTIDE_WORD_DIFFERENCE;
    }

    private static String generateRandomNucleotideWord() {
        var stringBuilder = new StringBuilder();
        Stream.generate(UniqueNucleotideWordsGenerator::getRandomNucleotide)
                .limit(NUCLEOTIDE_WORD_LENGTH)
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private static char getRandomNucleotide() {
        return NUCLEOTIDES[ThreadLocalRandom.current().nextInt(NUCLEOTIDES.length)];
    }
}
