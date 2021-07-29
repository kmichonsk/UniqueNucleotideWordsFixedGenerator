package org.example.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


public class UniqueNucleotideWordsGenerator {

    private static final int NUCLEOTIDE_WORD_LENGTH = 6;
    private static final int MINIMUM_NUCLEOTIDE_DIFFERENCE = 3;
    private static final char[] NUCLEOTIDES = {'A', 'T', 'G', 'C'};

    private UniqueNucleotideWordsGenerator() {
        throw new AssertionError("For simplicity (no di framework) " +
                "this should be used as an utility class!");
    }

    public static List<String> generateUniqueNucleotideWords(int numberOfWordsToGenerate) {
        final var list = new ArrayList<String>(numberOfWordsToGenerate);

        while (list.size() < numberOfWordsToGenerate) {
            final var word = generateRandomNucleotideWord();
            if (!containsTooSimilar(list, word)) {
                list.add(word);
                System.out.println("added(" + list.size() + ") " + word);
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
        return difference >= MINIMUM_NUCLEOTIDE_DIFFERENCE;
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
